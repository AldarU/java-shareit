package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.comments.mapper.CommentMapper;
import ru.practicum.shareit.comments.model.Comment;
import ru.practicum.shareit.comments.repository.CommentRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.request.service.RequestService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final BookingRepository bookingRepository;
    private final ItemMapper itemMapper;
    private final UserService userService;
    private final RequestService requestService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public ItemDto createItem(ItemDto itemDto, Long ownerId) {
        if (userService.getUserById(ownerId) != null) {
            Item item = ItemMapper.buildItem(itemDto);
            item.setOwnerId(ownerId);
            item.setItemRequest(itemDto.getRequestId() != null ?
                    RequestMapper.buildItemRequest(requestService.getById(itemDto.getRequestId(), ownerId)) : null);
            return ItemMapper.buildItemDto(itemRepository.save(item));
        } else {
            if (!itemDto.getAvailable()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Available null");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User не найден");
        }
    }


    @Override
    @Transactional
    public ItemDto updateItem(ItemDto item, Long id, Long ownerId) {
        if (item.getName() == null) {
            item.setName(item.getName());
        }
        if (item.getDescription() == null) {
            item.setDescription(item.getDescription());
        }
        if (item.getAvailable() == null) {
            item.setAvailable(item.getAvailable());
        }

        item.setId(id);
        item.setOwnerId(ownerId);
        item.setComments(CommentMapper.toDto(commentRepository.findByItemId(item.getId())));

        if (userService.getUserById(ownerId) != null) {
            Long oldOwnerItem = getItemById(id).getOwnerId();
            if (oldOwnerItem.equals(ownerId)) {
                return itemMapper.buildItemDto(itemRepository.save(itemMapper.buildItem(item)));
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not update with other owner");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User не найден");
        }
    }

    @Override
    @Transactional
    public ItemDto getItemById(Long itemId) {
        ItemDto item = itemMapper.buildItemDto(itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This itemId not found")));
        updateBookings(item);

        List<Comment> comments = commentRepository.findByItemId(item.getId());
        item.setComments(CommentMapper.toDto(comments));

        return item;
    }

    @Override
    @Transactional
    public List<ItemDto> getOwnerItems(Long ownerId) {
        return itemRepository
                .findAllByOwnerId(ownerId)
                .stream()
                .map(ItemMapper::buildItemDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemDto> getItemsByName(String text) {
        if (text.isBlank() || text == null) {
            return new ArrayList<>();
        }

        return itemRepository
                .getItemsByName(text)
                .stream()
                .map(ItemMapper::buildItemDto)
                .filter(ItemDto::getAvailable)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long getOwnerId(Long itemId) {
        return itemRepository.findById(itemId)
                .get()
                .getOwnerId();
    }

    @Override
    @Transactional
    public CommentDto addComment(Long itemId, Long userId, CommentDto commentDto) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemID not found."));
        User user = UserMapper.buildUser(userService.getUserById(userId));

        List<Booking> bookings = bookingRepository
                .findByItemIdAndBookerIdAndStatusIsAndEndIsBefore(itemId, userId, Status.APPROVED, LocalDateTime.now());

        if (!(!bookings.isEmpty() && bookings.get(0).getStart().isBefore(LocalDateTime.now()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Comment comment = CommentMapper.buildComment(commentDto);
        comment.setItem(item);
        comment.setAuthor(user);
        comment.setCreated(LocalDateTime.now());
        return CommentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public ItemDto updateBookings(ItemDto itemDto) {
        LocalDateTime now = LocalDateTime.now();
        List<Booking> bookings = bookingRepository.findBookingsItem(itemDto.getId());
        Booking lastBooking = bookings.stream()
                .filter(obj -> !(obj.getStatus().equals(Status.REJECTED)))
                .filter(obj -> obj.getStart().isBefore(now))
                .min((obj1, obj2) -> obj2.getStart().compareTo(obj1.getStart())).orElse(null);
        Booking nextBooking = bookings.stream()
                .filter(obj -> !(obj.getStatus().equals(Status.REJECTED)))
                .filter(obj -> obj.getStart().isAfter(now))
                .min(Comparator.comparing(Booking::getStart)).orElse(null);
        if (lastBooking != null) {
            itemDto.setLastBooking(BookingMapper.buildBookingDto(lastBooking));
        }
        if (nextBooking != null) {
            itemDto.setNextBooking(BookingMapper.buildBookingDto(nextBooking));
        }
        return itemDto;
    }
}

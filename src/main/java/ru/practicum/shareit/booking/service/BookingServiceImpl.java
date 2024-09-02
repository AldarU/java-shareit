package ru.practicum.shareit.booking.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ItemService itemService;

    @Override
    @Transactional
    public BookingDto createBooking(ShortBookingDto smallBooking, Long bookerId) {
        if (smallBooking.getEnd().isBefore(LocalDateTime.now())
                || smallBooking.getStart().isBefore(LocalDateTime.now())
                || smallBooking.getStart().isEqual(smallBooking.getEnd())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User booker = UserMapper.buildUser(userService.getUserById(bookerId));
        Item item = ItemMapper.buildItem(itemService.getItemById(smallBooking.getItemId(), bookerId));

        if (itemService.getOwnerId(item.getId()).equals(bookerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!item.getAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            Booking booking = Booking.builder().start(smallBooking.getStart()).end(smallBooking.getEnd())
                    .item(item).booker(booker).status(Status.WAITING).build();
            return BookingMapper.buildBookingDto(bookingRepository.save(booking));
        }
    }

    @Transactional
    @Override
    public BookingDto approveBooking(long bookingId, long userId, Boolean approve) {
        Booking booking = bookingRepository.getReferenceById(bookingId);

        if (!itemService.getOwnerId(booking.getItem().getId()).equals(userId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST approveBooking");
        }

        if (approve) {
            bookingRepository.save(bookingId, Status.APPROVED);
            booking.setStatus(Status.APPROVED);
        } else {
            bookingRepository.save(bookingId, Status.REJECTED);
            booking.setStatus(Status.REJECTED);
        }
        return BookingMapper.buildBookingDto(booking);
    }

    @Override
    @Transactional
    public BookingDto findBookingById(Long bookingId, Long userId) {
        return BookingMapper.buildBookingDto(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found.")));
    }

    @Override
    @Transactional
    public List<BookingDto> findBookingsByUser(String state, Long userId) {
        UserDto user = userService.getUserById(userId);
        LocalDateTime time = LocalDateTime.now();

        switch (state) {
            case "ALL" -> {
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByBookerIdOrderByStartDesc(userId));
            }
            case "CURRENT" -> {
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByBookerIdAndEndIsAfterAndStartIsBeforeOrderByStartDesc(userId, time, time));
            }
            case "PAST" -> {
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByBookerIdAndEndIsBeforeOrderByStartDesc(userId, time));
            }
            case "FUTURE" -> {
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByBookerIdAndStartIsAfterOrderByStartDesc(userId, time));
            }
            case "WAITING" -> {
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByBookerIdAndStartIsAfterAndStatusIsOrderByStartDesc(userId, time, Status.WAITING));
            }
            case "REJECTED" -> {
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByBookerIdAndStatusIsOrderByStartDesc(userId, Status.REJECTED));
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST IN FindBookingsByUser");
    }

    @Override
    @Transactional
    public List<BookingDto> findBookingsByOwner(String state, Long ownerId) {
        UserDto user = userService.getUserById(ownerId);
        LocalDateTime now = LocalDateTime.now();

        switch (state) {
            case "ALL":
                return BookingMapper.buildBookingDto(
                        bookingRepository.findByItemOwnerId(ownerId));
            case "CURRENT":
                return BookingMapper.buildBookingDto(
                        bookingRepository.findCurrentBookingsOwner(ownerId, now));
            case "PAST":
                return BookingMapper.buildBookingDto(
                        bookingRepository.findPastBookingsOwner(ownerId, now));
            case "FUTURE":
                return BookingMapper.buildBookingDto(
                        bookingRepository.findFutureBookingsOwner(ownerId, now));
            case "WAITING":
                return BookingMapper.buildBookingDto(
                        bookingRepository.findWaitingBookingsOwner(ownerId, now, Status.WAITING));
            case "REJECTED":
                return BookingMapper.buildBookingDto(
                        bookingRepository.findRejectedBookingsOwner(ownerId, Status.REJECTED));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST IN FindBookingsByOwner");
    }
}
package ru.practicum.shareit.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.comments.model.Comment;
import ru.practicum.shareit.comments.repository.CommentRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.request.dto.RequestDtoWithItems;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.service.RequestServiceImpl;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemServiceImpl itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private RequestServiceImpl requestService;
    private final User user = new User(1L, "User", "user@email.com");
    private final UserDto userDto = new UserDto(1L, "User", "user@email.com");

    private final ItemRequest itemRequest = ItemRequest.builder()
            .id(1L)
            .description("description")
            .requester(user)
            .items(new ArrayList<>())
            .build();
    private final RequestDtoWithItems itemRequestDto = RequestDtoWithItems.builder()
            .id(1L)
            .description("description")
            .requester(userDto)
            .items(new ArrayList<>())
            .build();
    private final Item item = Item.builder()
            .id(1L)
            .name("ItemName")
            .description("description")
            .available(true)
            .itemRequest(itemRequest)
            .ownerId(1L)
            .build();
    private final ItemDto itemDto = ItemDto.builder()
            .id(1L)
            .name("ItemName")
            .description("description")
            .available(true)
            .requestId(1L)
            .build();
    private final List<Booking> bookingList = List.of(Booking.builder()
                    .id(1L).item(item).booker(user)
                    .start(LocalDateTime.now().minusHours(2L))
                    .end(LocalDateTime.now().minusHours(1L))
                    .status(Status.WAITING).build(),
            Booking.builder()
                    .id(2L).item(item).booker(user)
                    .start(LocalDateTime.now().plusHours(1L))
                    .end(LocalDateTime.now().plusHours(2L))
                    .status(Status.WAITING).build());

    private final Comment comment = Comment.builder().id(1L).text("Text").item(item).author(user).build();
    private final CommentDto commentDto = CommentDto.builder().id(1L).text("Text").item(itemDto).authorName("User").build();

    @Test
    void createItemWhenAllIsValidThenReturnedExpectedItem() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenReturn(userDto);

        Mockito.when(requestService.getById(anyLong(), anyLong()))
                .thenReturn(itemRequestDto);

        Mockito.when(itemRepository.save(any()))
                .thenReturn(item);

        itemService.createItem(itemDto, 1L);
    }

    @Test
    void createItemWhenUserIsNotExistThenReturnedNotFoundException() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenThrow(new NotFoundException(String.format(String.format("User with ID = %d not found.", 100L))));

        Exception e = assertThrows(NotFoundException.class,
                () -> itemService.createItem(itemDto, 100L));

        assertEquals(e.getMessage(), String.format("User with ID = %d not found.", 100L));
    }

    @Test
    void test() throws IOException {
    }

    @Test
    void testNull() {

    }

    @Test
    void testUpdateLaterAldar() {
    }

    @Test
    void findByIdWhenParamsIsValidThenReturnedExpectedItem() {
        Mockito.when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.of(item));

        Mockito.when(commentRepository.findByItemId(anyLong()))
                .thenReturn(List.of(comment));

        Mockito.when(bookingRepository.findBookingsItem(anyLong()))
                .thenReturn(bookingList);

        ItemDto requestedItemDto = itemService.getItemById(1L, 1L);

        assertEquals(requestedItemDto.getName(), item.getName());
        assertEquals(requestedItemDto.getDescription(), item.getDescription());
        assertEquals(requestedItemDto.getAvailable(), item.getAvailable());
        assertEquals(requestedItemDto.getLastBooking().getId(), 1L);
        assertEquals(requestedItemDto.getLastBooking().getBookerId(), 1L);
        assertEquals(requestedItemDto.getNextBooking().getId(), 2L);
        assertEquals(requestedItemDto.getNextBooking().getBookerId(), 1L);
    }

    @Test
    void findByIdWhenItemNotFoundThenReturnedNotFoundException() {
        Mockito.when(itemRepository.findById(anyLong()))
                .thenThrow(new NotFoundException(String.format("Item with ID = %d not found.", 100L)));

        Exception e = assertThrows(NotFoundException.class, () -> itemService
                .getItemById(100L, 1L));

        assertEquals(e.getMessage(), String.format("Item with ID = %d not found.", 100L));
    }

    @Test
    void findAllUserItemsWhenAllParamsIsValidThenReturnedListItems() {
        Mockito.when(itemRepository.findAllByOwnerId(anyLong()))
                .thenReturn(List.of(item));

        Mockito.when(bookingRepository.findBookingsItem(anyLong()))
                .thenReturn(bookingList);

        List<ItemDto> userItemsList = itemService.getOwnerItems(1L);

        assertEquals(userItemsList.get(0).getLastBooking().getId(), 1L);
        assertEquals(userItemsList.get(0).getLastBooking().getBookerId(), 1L);
        assertEquals(userItemsList.get(0).getNextBooking().getId(), 2L);
        assertEquals(userItemsList.get(0).getNextBooking().getBookerId(), 1L);
    }

    @Test
    void updateItemWhenAllParamsIsValidThenReturnedUpdatedItem() {
        ItemDto itemDtoUpdate = ItemDto.builder()
                .id(1L)
                .name("ItemUpdate")
                .description("DescriptionUpdate")
                .available(true)
                .requestId(1L)
                .build();

        Mockito.when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.of(item));
        Mockito.when(userService.getUserById(anyLong()))
                .thenReturn(userDto);
        Mockito.when(itemRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        itemService.updateItem(itemDtoUpdate, 1L, 1L);
    }

    @Test
    void updateItemWhenItemIsNotFoundThenReturnedNotFoundException() {
        Mockito.when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Exception e = assertThrows(NotFoundException.class,
                () -> itemService.updateItem(itemDto, 100L, 1L));

        assertEquals(e.getMessage(), String.format("Item is not found", 100L));
    }


    @Test
    void addCommentTest() {
        Mockito.when(itemRepository.findById(anyLong()))
                .thenReturn(Optional.of(item));
        Mockito.when(userService.getUserById(anyLong()))
                .thenReturn(userDto);
        Mockito.when(bookingRepository.findByItemIdAndBookerIdAndStatusIsAndEndIsBefore(anyLong(), anyLong(), any(), any()))
                .thenReturn(bookingList);
        Mockito.when(commentRepository.save(any()))
                .thenReturn(comment);
        Mockito.when(commentRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));
        CommentDto testComment = itemService.addComment(1L, 1L, commentDto);

        assertEquals(testComment.getId(), commentDto.getId());
        assertEquals(testComment.getText(), commentDto.getText());
        assertEquals(testComment.getAuthorName(), commentDto.getAuthorName());
    }
}
package ru.practicum.shareit.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ItemServiceImpl itemService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ItemRepository itemRepository;

    private final User user1 = new User(1L, "User", "user@email.com");
    private final User user2 = new User(2L, "User2", "user2@email.com");
    private final UserDto userDto = new UserDto(1L, "User", "user@email.com");
    private final ShortBookingDto shortBookingDto = ShortBookingDto.builder()
            .start(LocalDateTime.now())
            .end(LocalDateTime.now().plusHours(1L))
            .itemId(1L)
            .build();
    private final ItemDto itemDto = ItemDto.builder()
            .id(1L)
            .name("Item")
            .description("Description")
            .available(true)
            .requestId(1L)
            .build();
    private final Item item = Item.builder()
            .id(1L)
            .name("Item")
            .description("Description")
            .available(true)
            .ownerId(1L)
            .build();

    private final Item item2 = Item.builder()
            .id(1L)
            .name("Item2")
            .description("Description2")
            .available(true)
            .ownerId(2L)
            .build();

    private final Booking booking1 = Booking.builder()
            .booker(user1)
            .id(1L)
            .status(Status.APPROVED)
            .item(item).build();

    @ParameterizedTest
    @CsvSource(value = {
            "ALL, 0, 2",
            "CURRENT, -1, 1",
            "PAST, -2, -1",
            "FUTURE, 1, 2",
            "WAITING, 0, 1",
    })
    void getByUserIdAndStateTest(String state, int addToStart, int addToEnd) {
        LocalDateTime start = LocalDateTime.now().plusDays(addToStart);
        LocalDateTime end = LocalDateTime.now().plusDays(addToEnd);
        User testUser1 = new User(1L, "User", "user@email.com");
        Item testItem = item2;
        Booking testBooking = booking1;
        testBooking.setBooker(testUser1);
        testBooking.setStart(start);
        testBooking.setEnd(end);

        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testItem));
        Mockito.when(userRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(bookingRepository.save(any(Booking.class))).thenReturn(testBooking);

        Mockito.when(bookingRepository.findByBookerIdOrderByStartDesc(Mockito.anyLong()))
                .thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findByBookerIdAndEndIsAfterAndStartIsBeforeOrderByStartDesc(
                Mockito.anyLong(),any(), any())).thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findByBookerIdAndEndIsBeforeOrderByStartDesc(Mockito.anyLong(),
                any())).thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findByBookerIdAndStartIsAfterOrderByStartDesc(Mockito.anyLong(),
                any())).thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findByBookerIdAndStartIsAfterAndStatusIsOrderByStartDesc(Mockito.anyLong(),
                any(), any())).thenReturn(List.of(testBooking));
        List<BookingDto> bookings = bookingService.findBookingsByUser(state, testUser1.getId());
        assertFalse(bookings.isEmpty());
        assertEquals(bookings.get(0).getId(), 1L);
    }

    @Test
    void test() {
    }

    @Test
    void testNull() {
    }

    @Test
    void testUpdateLaterAldar() {
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ALL, 0, 2",
            "CURRENT, -1, 1",
            "PAST, -2, -1",
            "FUTURE, 1, 2",
    })
    void getByItemOwnerIdAndStateTest(String state, int addToStart, int addToEnd) {
        LocalDateTime start = LocalDateTime.now().plusDays(addToStart);
        LocalDateTime end = LocalDateTime.now().plusDays(addToEnd);
        User booker = new User(1L, "User", "user@email.com");;
        User itemOwner = new User(2L, "User2", "user2@email.com");;
        Item testItem = Item.builder()
            .id(1L)
            .name("Item")
            .description("Description")
            .available(true)
            .ownerId(2L)
            .build();
        Booking testBooking = Booking.builder()
            .booker(user1)
            .id(1L)
            .status(Status.APPROVED)
            .item(item).build();
        testBooking.setBooker(booker);
        testBooking.setStart(start);
        testBooking.setEnd(end);
        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testItem));
        Mockito.when(userRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(bookingRepository.save(any(Booking.class))).thenReturn(testBooking);
        Mockito.when(bookingRepository.findByItemOwnerId(Mockito.anyLong()))
                .thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findCurrentBookingsOwner(
                Mockito.anyLong(),any())).thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findByBookerIdAndEndIsBeforeOrderByStartDesc(Mockito.anyLong(),
                any())).thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findPastBookingsOwner(
                Mockito.anyLong(),any())).thenReturn(List.of(testBooking));
        Mockito.when(bookingRepository.findFutureBookingsOwner(Mockito.anyLong(),
                any())).thenReturn(List.of(testBooking));
        List<BookingDto> bookings = bookingService.findBookingsByOwner(state, itemOwner.getId());
        assertEquals(1, bookings.size());
        assertEquals(bookings.get(0).getId(), 1L);
    }
}
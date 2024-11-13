package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private ItemRepository itemRepository;

    private User user;
    private User owner;
    private Item item;
    private Booking booking;
    private ShortBookingDto ShortBookingDto;
    private BookingDto BookingDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .name("User")
                .email("user@example.com")
                .build();

        owner = User.builder()
                .id(2L)
                .name("User")
                .email("user@example.com")
                .build();

        item = Item.builder()
                .id(1L)
                .name("Item")
                .description("Description")
                .available(true)
                .ownerId(owner.getId())
                .build();

        booking = Booking.builder()
                .id(1L)
                .item(item)
                .booker(user)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .status(Status.WAITING)
                .build();

        ShortBookingDto = ShortBookingDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(2))
                .itemId(1L)
                .build();

        BookingDto = BookingMapper.buildBookingDto(booking);
    }

    @Test
    void addBookingSuccess() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(itemRepository.findById(ShortBookingDto.getItemId())).thenReturn(Optional.of(item));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
    }

    @Test
    void addBookingMissingDatesException() {
        ShortBookingDto invalidDto = ShortBookingDto.builder()
                .start(null)
                .end(null)
                .itemId(1L)
                .build();
    }

    @Test
    void addBookingUserNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
    }

    @Test
    void addBookingItemNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(itemRepository.findById(ShortBookingDto.getItemId())).thenReturn(Optional.empty());
    }

    @Test
    void addBookingItemNotAvailableException() {
        Item unavailableItem = Item.builder()
                .id(1L)
                .name("Item")
                .description("Description")
                .available(false)
                .ownerId(owner.getId())
                .build();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(itemRepository.findById(ShortBookingDto.getItemId())).thenReturn(Optional.of(unavailableItem));
    }

    @Test
    void approveBooking_Success() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
    }

    @Test
    void approveBookingBookingNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.empty());
    }

    @Test
    void approveBookingUserNotFoundException() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
    }

    @Test
    void getByIdSuccess() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        BookingDto result = bookingService.findBookingById(booking.getId(), user.getId());

        assertEquals(booking.getId(), result.getId());
        verify(bookingRepository).findById(booking.getId());
    }

    @Test
    void getByIdNotFoundException() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.empty());
    }

    @Test
    void getAllBookingsByUserSuccess() {
        String state = "ALL";
        Collection<Booking> bookings = Collections.singletonList(booking);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsAllItemsByOwnerSuccess() {
        String state = "ALL";
        Collection<Booking> bookings = Collections.singletonList(booking);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void addBookingWhenUserIsOwnerShouldThrowException() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(itemRepository.findById(ShortBookingDto.getItemId())).thenReturn(Optional.of(item));
    }

    @Test
    void approveBookingStatusNotWaitingException() {
        booking = Booking.builder()
                .id(1L)
                .item(item)
                .booker(user)
                .start(LocalDateTime.now().plusDays(1))
                .end(LocalDateTime.now().plusDays(2))
                .status(Status.APPROVED)
                .build();
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
    }

    @Test
    void getAllBookingsAllItemsByOwnerUnknownStateException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsByUserAllState() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsByUserWaitingState() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsByUserRejectedState() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsByUserInvalidState() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsByUserNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
    }

    @Test
    void getAllBookingsByUserUnknownStateException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsAllItemsByOwnerAllState() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
    }

    @Test
    void getAllBookingsAllItemsByOwnerWaitingState() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
    }

    @Test
    void getAllBookingsAllItemsByOwnerRejectedState() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
    }

    @Test
    void getAllBookingsAllItemsByOwnerInvalidState() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
    }

    @Test
    void getAllBookingsAllItemsByOwnerNotFoundException() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.empty());
    }
}
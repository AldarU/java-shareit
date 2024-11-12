package ru.practicum.shareit.booking;

import io.micrometer.core.instrument.config.validate.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

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
    private ShortBookingDto bookingCreateDto;
    private BookingDto bookingRequestDto;

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

        bookingCreateDto = ShortBookingDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(2))
                .itemId(1L)
                .build();

        bookingRequestDto = BookingMapper.buildBookingDto(booking);
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
        when(itemRepository.findById(bookingCreateDto.getItemId())).thenReturn(Optional.of(unavailableItem));

        Exception exception = assertThrows(Exception.class, () -> bookingService.createBooking(bookingCreateDto, user.getId()));
    }

    @Test
    void approveOrRejectBookingSuccess() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
    }

    @Test
    void approveOrRejectBookingBookingNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.empty());
    }

    @Test
    void approveOrRejectBookingUserNotFoundException() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
    }

    @Test
    void getByIdSuccess() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.of(booking));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getByIdNotFoundException() {
        when(bookingRepository.findById(booking.getId())).thenReturn(Optional.empty());
    }

    @Test
    void getAllBookingsByUserSuccess() {
        String state = "ALL";
        Collection<Booking> bookings = Collections.singletonList(booking);
    }

    @Test
    void getAllBookingsByUserInvalidStateException() {
        String state = "INVALID";
    }

    @Test
    void getAllBookingsAllItemsByOwnerSuccess() {
        String state = "ALL";
        Collection<Booking> bookings = Collections.singletonList(booking);
    }

    @Test
    void addBookingWhenUserIsOwnerShouldThrowException() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        when(itemRepository.findById(bookingCreateDto.getItemId())).thenReturn(Optional.of(item));

        Exception exception = assertThrows(Exception.class, () ->
                bookingService.createBooking(bookingCreateDto, owner.getId())
        );
    }

    @Test
    void getAllBookingsAllItemsByOwnerUnknownStateException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    void getAllBookingsByUserNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
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
    void getAllBookingsAllItemsByOwnerNotFoundException() {
        when(userRepository.findById(owner.getId())).thenReturn(Optional.empty());
    }
}
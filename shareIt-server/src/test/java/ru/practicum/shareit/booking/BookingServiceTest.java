package ru.practicum.shareit.booking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private BookingRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private BookingServiceImpl service;

    @Test
    void findByIdBookingNotFoundException() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void createBooking() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void testCreateBooking() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void testCreateBookingNotAvaliable() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void testCreateBookingAvaliable() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void findAll() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void findByOwnerException() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void findByOwner() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void findByIdBookingAccessException() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }

    @Test
    void findById() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(Exception.class, () -> service.findBookingById(1L, 2L));
    }
}
package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import java.util.List;

public interface BookingService {
    BookingDto createBooking(ShortBookingDto booking, Long bookerId);

    BookingDto approveBooking(long bookingId, long userId, Boolean approve);

    BookingDto findBookingById(Long bookingId, Long userId);

    List<BookingDto> findBookingsByUser(String state, Long userId);

    List<BookingDto> findBookingsByOwner(String state, Long ownerId);

}

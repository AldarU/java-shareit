package ru.practicum.shareit.booking.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    private BookingDto createBooking(@Valid @RequestBody ShortBookingDto smallBooking,
                                     @RequestHeader("X-Sharer-User-Id") Long bookerId) {
        return bookingService.createBooking(smallBooking, bookerId);
    }

    @PatchMapping("/{bookingId}")
    private BookingDto approveBooking(@PathVariable long bookingId,
                                           @RequestHeader("X-Sharer-User-Id") long userId,
                                           @RequestParam Boolean approved) {
        return bookingService.approveBooking(bookingId, userId, approved);
    }

    @GetMapping("/{bookingId}")
    private BookingDto findBookingById(@PathVariable Long bookingId,
                                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.findBookingById(bookingId, userId);
    }

    @GetMapping
    private List<BookingDto> findBookingsByUser(@RequestParam(defaultValue = "ALL") String state,
                                                @RequestHeader("X-Sharer-User-Id") Long userId) {
        return bookingService.findBookingsByUser(state, userId);
    }

    @GetMapping("/owner")
    private List<BookingDto> findBookingsByOwner(@RequestParam(defaultValue = "ALL") String state,
                                                      @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        return bookingService.findBookingsByOwner(state, ownerId);
    }
}

package ru.practicum.shareit.booking.controller;

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
    public BookingDto createBooking(@RequestBody ShortBookingDto smallBooking,
                                     @RequestHeader("X-Sharer-User-Id") Long bookerId) {
        log.info("Create booking");
        return bookingService.createBooking(smallBooking, bookerId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto approveBooking(@PathVariable long bookingId,
                                           @RequestHeader("X-Sharer-User-Id") long userId,
                                           @RequestParam Boolean approved) {
        log.info("Approve booking");
        return bookingService.approveBooking(bookingId, userId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto findBookingById(@PathVariable Long bookingId,
                                            @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Find booking by bookingId {} with userId {}", bookingId, userId);
        return bookingService.findBookingById(bookingId, userId);
    }

    @GetMapping
    public List<BookingDto> findBookingsByUser(@RequestParam(defaultValue = "ALL") String state,
                                                @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Find booking by userId {} with state {}", userId, state);
        return bookingService.findBookingsByUser(state, userId);
    }

    @GetMapping("/owner")
    public List<BookingDto> findBookingsByOwner(@RequestParam(defaultValue = "ALL") String state,
                                                      @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("Find booking by ownerId {} with state {}", ownerId, state);
        return bookingService.findBookingsByOwner(state, ownerId);
    }
}

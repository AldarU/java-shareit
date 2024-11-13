package ru.practicum.shareit.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ItemBookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingMapperTest {

    private User user;
    private Item item;
    private Booking booking;
    private ShortBookingDto bookingCreateDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .build();

        item = Item.builder()
                .id(1L)
                .name("ItemName")
                .description("ItemDescription")
                .available(true)
                .ownerId(user.getId())
                .build();

        booking = Booking.builder()
                .id(1L)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .item(item)
                .booker(user)
                .status(Status.WAITING)
                .build();

        bookingCreateDto = ShortBookingDto.builder()
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    void shouldMapBookingToBookingRequestDto() {
        BookingDto bookingRequestDto = BookingMapper.buildBookingDto(booking);

        assertNotNull(bookingRequestDto);
        assertEquals(booking.getId(), bookingRequestDto.getId());
        assertEquals(booking.getStart(), bookingRequestDto.getStart());
        assertEquals(booking.getEnd(), bookingRequestDto.getEnd());
        assertEquals(booking.getStatus(), bookingRequestDto.getStatus());

        ItemDto itemNameDto = bookingRequestDto.getItem();
        assertNotNull(itemNameDto);
        assertEquals(item.getId(), itemNameDto.getId());
        assertEquals(item.getName(), itemNameDto.getName());

        UserDto userNameDto = bookingRequestDto.getBooker();
        assertNotNull(userNameDto);
        assertEquals(user.getId(), userNameDto.getId());
        assertEquals(user.getName(), userNameDto.getName());
    }

    @Test
    void shouldReturnBookingItemDtoWhenBookingIsNotNull() {
        ItemBookingDto bookingItemDto = BookingMapper.buildItemBookingDto(booking);

        assertNotNull(bookingItemDto);
        assertEquals(booking.getId(), bookingItemDto.getId());
        assertEquals(booking.getStart(), bookingItemDto.getStart());
        assertEquals(booking.getEnd(), bookingItemDto.getEnd());
        assertEquals(booking.getBooker().getId(), bookingItemDto.getBookerId());
    }

    @Test
    void shouldMapBookingCollectionToBookingRequestDtoCollection() {
        List<Booking> bookings = Arrays.asList(booking);
        Collection<BookingDto> bookingRequestDtos = BookingMapper.buildBookingDto(bookings);

        assertNotNull(bookingRequestDtos);
        assertEquals(1, bookingRequestDtos.size());

        BookingDto bookingRequestDto = bookingRequestDtos.iterator().next();
        assertEquals(booking.getId(), bookingRequestDto.getId());
    }

    @Test
    void shouldReturnEmptyCollectionWhenBookingsIsEmpty() {
        List<Booking> bookings = Collections.emptyList();
        Collection<BookingDto> bookingRequestDtos = BookingMapper.buildBookingDto(bookings);

        assertNotNull(bookingRequestDtos);
        assertTrue(bookingRequestDtos.isEmpty());
    }
}
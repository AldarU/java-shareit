package ru.practicum.shareit.booking.mapper;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookingMapperTest {


    @Test
    void mapBookingCreateDtoToBooking() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(5);

        ShortBookingDto crdto = new ShortBookingDto();
        crdto.setStart(start);
        crdto.setEnd(end);
    }

    @Test
    void mapBookingToBoodingDto() {

        Item item = new Item();
        item.setId(1L);
        item.setName("name");
        User user = new User();
        user.setId(2L);
        user.setName("name");
        user.setEmail("email@email.dk");
        Booking booking = Booking.builder()
                .status(Status.WAITING)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(6))
                .item(item)
                .booker(user)
                .id(3L)
                .build();

        BookingDto result = BookingMapper.buildBookingDto(booking);

        Assertions.assertEquals(booking.getId(), result.getId());
        Assertions.assertEquals(booking.getStart(), result.getStart());
        Assertions.assertEquals(booking.getEnd(), result.getEnd());
        Assertions.assertEquals(booking.getBooker().getName(), result.getBooker().getName());
        Assertions.assertEquals(booking.getItem().getId(), result.getItem().getId());

    }
}
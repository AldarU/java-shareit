package ru.practicum.shareit.booking.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.user.mapper.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {
    public static BookingDto buildBookingDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .booker(UserMapper.buildUserDto(booking.getBooker()))
                .item(ItemMapper.buildItemDto(booking.getItem()))
                .status(booking.getStatus())
                .build();
    }

    public static List<BookingDto> buildBookingDto(List<Booking> list) {
        return list.stream()
                .map(BookingMapper::buildBookingDto)
                .collect(Collectors.toList());
    }

}

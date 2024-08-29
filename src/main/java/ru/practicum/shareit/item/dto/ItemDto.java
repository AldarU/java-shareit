package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ItemBookingDto;
import ru.practicum.shareit.comments.dto.CommentDto;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ItemDto {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private Boolean available;

    private Long ownerId;

    private ItemBookingDto lastBooking;

    private ItemBookingDto nextBooking;

    private List<CommentDto> comments;

    private Long requestId;
}

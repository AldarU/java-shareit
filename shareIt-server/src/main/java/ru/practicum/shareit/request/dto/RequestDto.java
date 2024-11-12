package ru.practicum.shareit.request.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class RequestDto {
    private Long id;
    private String description;
    private UserDto requester;
    private LocalDateTime created;
    //private List<ItemDto> items;
}
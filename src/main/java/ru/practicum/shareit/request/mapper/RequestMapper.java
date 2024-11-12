package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.mapper.UserMapper;

import java.util.stream.Collectors;

@Component
public class RequestMapper {
    public static RequestDto buildItemRequestDto(ItemRequest request) {
        return RequestDto.builder()
                .id(request.getId())
                .description(request.getDescription())
                .created(request.getCreated())
                .requester(UserMapper.buildUserDto(request.getRequester()))
                .items(request.getItems()
                        .stream()
                        .map(ItemMapper::buildItemDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ItemRequest buildItemRequest(RequestDto requestDto) {
        return ItemRequest.builder()
                .id(requestDto.getId())
                .description(requestDto.getDescription())
                .requester(UserMapper.buildUser(requestDto.getRequester()))
                .created(requestDto.getCreated())
                .build();
    }
}

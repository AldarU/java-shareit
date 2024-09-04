package ru.practicum.shareit.request.service;

import ru.practicum.shareit.request.dto.RequestDto;

import java.util.List;

public interface RequestService {
    RequestDto create(RequestDto requestDto, Long userId);

    RequestDto getById(Long userId, Long requestId);

    List<RequestDto> findRequests(Long userId);

    List<RequestDto> findRequestsByUser(Long userId);
}

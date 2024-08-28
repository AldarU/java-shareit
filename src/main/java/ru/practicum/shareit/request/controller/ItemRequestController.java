package ru.practicum.shareit.request.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@AllArgsConstructor
public class ItemRequestController {
    private final RequestService requestService;

    @PostMapping
    public RequestDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                             @RequestBody RequestDto requestDto) {
        return requestService.create(requestDto, userId);
    }

    @GetMapping("{requestId}")
    public RequestDto getById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                          @PathVariable Long requestId) {
        return requestService.getById(userId, requestId);
    }

    @GetMapping("/all")
    public List<RequestDto> findRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.findRequests(userId);
    }

    @GetMapping
    public List<RequestDto> getUserRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return requestService.findRequestsByUser(userId);
    }
}

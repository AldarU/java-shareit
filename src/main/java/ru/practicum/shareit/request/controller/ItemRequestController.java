package ru.practicum.shareit.request.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/requests")
@AllArgsConstructor
public class ItemRequestController {
    private final RequestService requestService;

    @PostMapping
    public RequestDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                             @RequestBody RequestDto requestDto) {
        log.info("Create itemRequest");
        return requestService.create(requestDto, userId);
    }

    @GetMapping("{requestId}")
    public RequestDto getById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                          @PathVariable Long requestId) {
        log.info("Get request by id {}", requestId);
        return requestService.getById(userId, requestId);
    }

    @GetMapping("/all")
    public List<RequestDto> findRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Find requests by id {}", userId);
        return requestService.findRequests(userId);
    }

    @GetMapping
    public List<RequestDto> getUserRequests(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Get user request by id {}", userId);
        return requestService.findRequestsByUser(userId);
    }
}

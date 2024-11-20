package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestCreateDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {

    private final ItemRequestService service;

    @PostMapping
    public ItemRequestDto createItemRequest(@RequestBody ItemRequestCreateDto itemRequestCreateDto,
                                            @RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        return service.createItemRequest(itemRequestCreateDto, userId);
    }

    @GetMapping
    public List<ItemRequestDto> getItemRequests(@RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        return service.getItemRequests(userId);
    }

    @GetMapping("/{id}")
    public ItemRequestDto getItemRequestById(@PathVariable long id,
                                             @RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        return service.getItemRequestById(id, userId);
    }
}

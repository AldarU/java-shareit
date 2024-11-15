package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ItemDto createItem(@Valid @RequestBody ItemDto item, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("Create item");
        return itemService.createItem(item, ownerId);
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@RequestBody ItemDto item, @PathVariable Long id, @RequestHeader("X-Sharer-User-Id") Long ownerId) {
        log.info("Update item");
        return itemService.updateItem(item, id, ownerId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable Long itemId, @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Get item by id {} {}", itemId, userId);
        return itemService.getItemById(itemId, userId);
    }

    @GetMapping
    public List<ItemDto> getItems(@RequestHeader("X-Sharer-User-Id") Long id) {
        log.info("Get items by Onwer Id {}", id);
        return itemService.getOwnerItems(id);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsByName(@RequestParam String text) {
        log.info("Get item by name {}", text);
        return itemService.getItemsByName(text);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@RequestHeader("X-Sharer-User-Id") Long userId, @PathVariable Long itemId, @RequestBody CommentDto commentDto) {
        log.info("Received a POST-request to the endpoint: '/items/{itemId}/comment' to add a comment");
        return itemService.addComment(itemId, userId, commentDto);
    }
}

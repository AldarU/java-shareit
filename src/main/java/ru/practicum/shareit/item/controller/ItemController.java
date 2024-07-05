package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable Long id) {
        log.info("Get item by id {}", id);
        return itemService.getItemById(id);
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
}

package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto item, Long ownerId);

    ItemDto updateItem(ItemDto item, Long id, Long ownerId);

    ItemDto getItemById(Long itemId);

    List<ItemDto> getOwnerItems(Long ownerId);

    List<ItemDto> getItemsByName(String text);
}

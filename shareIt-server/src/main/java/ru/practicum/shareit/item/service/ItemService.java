package ru.practicum.shareit.item.service;

import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto item, Long ownerId);

    ItemDto updateItem(ItemDto item, Long id, Long ownerId);

    ItemDto getItemById(Long itemId, Long userId);

    List<ItemDto> getOwnerItems(Long ownerId);

    List<ItemDto> getItemsByName(String text);

    Long getOwnerId(Long itemId);

    CommentDto addComment(Long itemId, Long userId, CommentDto commentDto);

    ItemDto updateBookings(ItemDto itemDto);
}

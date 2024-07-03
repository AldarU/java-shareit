package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {
    Item createItem(Item item, Long ownerId);

    Item updateItem(Item item, Long id, Long ownerId);

    Item getItemById(Long itemId);

    List<Item> getOwnerItems(Long ownerId);

    List<Item> getItemsByName(String text);

}

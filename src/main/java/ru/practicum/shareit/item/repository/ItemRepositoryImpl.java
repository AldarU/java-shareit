package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> itemMap = new HashMap<>();
    private Long itemId = 0L;

    @Override
    public Item createItem(Item item, Long ownerId) {
        itemId++;
        item.setId(itemId);
        item.setOwnerId(ownerId);

        itemMap.put(itemId, item);

        return item;
    }

    @Override
    public Item updateItem(Item item, Long id, Long ownerId) {
        itemMap.put(id, item);
        return item;
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemMap.get(itemId);
    }

    @Override
    public List<Item> getOwnerItems(Long ownerId) {
        return itemMap.values()
                .stream()
                .filter(item -> item.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getItemsByName(String text) {
        return itemMap.values()
                .stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase())
                        || item.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }
}

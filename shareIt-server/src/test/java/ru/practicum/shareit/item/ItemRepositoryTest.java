package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRepositoryTest {
    private final ItemRepository itemRepository;

    @Test
    void createItem() {
        Item item = new Item();
        item.setName("name");
    }

    @Test
    void updateItem() {
        Item item = new Item();
        item.setName("name");
        item.setName("name739");
    }

    @Test
    void getItems() {
        Item item = new Item();
        item.setName("name");
    }

    @Test
    void getItemById() {
        Item item = new Item();
        item.setName("name");
    }

    @Test
    void searchItem() {
        Item item = new Item();
        item.setName("name");
        item.setAvailable(true);
    }

    @Test
    void deleteItem() {
        Item item = new Item();
        item.setName("name");
        item.setAvailable(true);
    }

}
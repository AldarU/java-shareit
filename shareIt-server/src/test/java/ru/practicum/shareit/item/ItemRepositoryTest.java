package ru.practicum.shareit.item;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.item.model.Item;

@DataJpaTest
public class ItemRepositoryTest {
    @Test
    void createItem() {
        Item item = new Item();
        item.setName("name");
    }

    @Test
    void updateItem() {
        Item item = new Item();
        item.setName("name");

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

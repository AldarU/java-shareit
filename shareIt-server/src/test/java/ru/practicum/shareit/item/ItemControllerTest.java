package ru.practicum.shareit.item;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.practicum.shareit.item.controller.ItemController;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {
    @Test
    @SneakyThrows
    public void createItem() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    @SneakyThrows
    public void updateItem() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    @SneakyThrows
    public void getItems() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @SneakyThrows
    @Test
    public void getItemById() {

        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    @SneakyThrows
    public void searchItem() {

        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        String text = "ame";
    }


    @Test
    @SneakyThrows
    public void createComment() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }
}

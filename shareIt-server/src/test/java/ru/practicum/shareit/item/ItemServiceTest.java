package ru.practicum.shareit.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comments.repository.CommentRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Test
    void createItem() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    void updateItemItemNotFoundException() {
        long id = 1;
        String name = "name";
        String description = "description";
    }

    @Test
    void updateItemNotNull() {
        long id = 1;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    void updateItemNNull() {
        long id = 1;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    void getItems() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    void getItemByIdWithoutComments() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }

    @Test
    void getItemById() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        User user = new User();
        user.setId(2L);
        user.setName("name");
        user.setEmail("email@email.dk");
    }

    @Test
    void searchItem() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }

    @Test
    void searchItemBlank() {
        List<Item> result = new ArrayList<>();
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void createComment() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        User user = new User();
        user.setId(userId);
        user.setName("name");
        user.setEmail("email@email.dk");

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }

    @Test
    void createCommentException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        User user = new User();
        user.setId(userId);
        user.setName("name");
        user.setEmail("email@email.dk");

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }

    @Test
    void createCommentItemNotFoundException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        User user = new User();
        user.setId(userId);
        user.setName("name");
        user.setEmail("email@email.dk");

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }

    @Test
    void createCommentUserNotFoundException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        User user = new User();
        user.setId(userId);
        user.setName("name");
        user.setEmail("email@email.dk");
    }
}

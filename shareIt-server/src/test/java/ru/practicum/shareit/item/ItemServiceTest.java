package ru.practicum.shareit.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comments.repository.CommentRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ItemServiceImpl service;

    @Test
    void createItem() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void updateItemItemNotFoundException() {
        long id = 1;
        String name = "name";
        String description = "description";
        boolean available = true;
    }

    @Test
    void updateItemNotNull() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void updateItemNNull() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void getItems() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void getItemByIdItemNotFoundException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void getItemByIdWithoutComments() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void getItemById() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void searchItem() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void searchItemBlank() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void createComment() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void createCommentException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void createCommentItemNotFoundException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }

    @Test
    void createCommentUserNotFoundException() {
        long id = 1;
        long userId = 3;
        String name = "name";
        String description = "description";
        boolean available = true;
        ItemDto itemDtoCreate = ItemDto.builder()
                .name(name)
                .available(available)
                .description(description)
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name(name)
                .available(available)
                .id(id)
                .ownerId(userId)
                .description(description)
                .build();

        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
        item.setOwnerId(userId);
        item.setDescription(description);
    }
}
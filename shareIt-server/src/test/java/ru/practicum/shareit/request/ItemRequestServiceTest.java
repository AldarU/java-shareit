package ru.practicum.shareit.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.request.service.RequestServiceImpl;

@ExtendWith(MockitoExtension.class)
class ItemRequestServiceTest {

    @Mock
    private RequestRepository repository;
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private RequestServiceImpl service;

    @Test
    void createItemRequest() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
    }

    @Test
    void getItemRequests() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
    }

    @Test
    void getItemRequestByIdNull() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
        String name = "name";
        boolean available = true;
        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }

    @Test
    void getItemRequestById() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
        String name = "name";
        boolean available = true;
        Item item = new Item();
        item.setName(name);
        item.setAvailable(available);
        item.setId(id);
    }
}
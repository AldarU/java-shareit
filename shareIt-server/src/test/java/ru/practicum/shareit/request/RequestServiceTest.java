package ru.practicum.shareit.request;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.request.service.RequestServiceImpl;

@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @InjectMocks
    private RequestServiceImpl service;

    @Test
    public void createItemRequest() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
    }

    @Test
    public void getItemRequests() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
    }

    @Test
    public void getItemRequestById() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
        String name = "name";
        boolean available = true;
    }
}

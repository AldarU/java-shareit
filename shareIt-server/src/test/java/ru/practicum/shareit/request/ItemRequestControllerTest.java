package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.practicum.shareit.request.controller.ItemRequestController;
import ru.practicum.shareit.request.dto.RequestDtoWithItems;

@Slf4j
@WebMvcTest(ItemRequestController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestControllerTest {
    @Test
    @SneakyThrows
    void createItemRequest() {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
        RequestDtoWithItems crdto = new RequestDtoWithItems();
        crdto.setDescription(description);
    }

    @Test
    void getItemRequests() throws Exception {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
    }

    @Test
    void getItemRequestById() throws Exception {
        Long userId = (long) 1;
        String description = "description";
        Long id = (long) 123;
    }
}
package ru.practicum.shareit.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.request.controller.ItemRequestController;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestService;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

@Slf4j
@WebMvcTest(ItemRequestController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RequestControllerTest {

    @MockBean
    private final RequestService service;
    private final ObjectMapper mapper;
    private final MockMvc mockMvc;

    @Test
    @SneakyThrows
    void createItemRequest() {
        String description = "description";
        Long id = (long) 123;
        UserDto userDto = new UserDto(id, "", "mail@mail.ru");

        RequestDto dto = new RequestDto();
        dto.setId(id);
        dto.setDescription(description);
        dto.setRequester(userDto);
        dto.setCreated(LocalDateTime.now());
    }

    @Test
    void getItemRequests() throws Exception {
        String description = "description";
        Long id = (long) 123;
        UserDto userDto = new UserDto(id, "", "mail@mail.ru");

        RequestDto dto = new RequestDto();
        dto.setId(id);
        dto.setDescription(description);
        dto.setRequester(userDto);
        dto.setCreated(LocalDateTime.now());
    }

    @Test
    void getItemRequestById() throws Exception {
        String description = "description";
        Long id = (long) 123;
        UserDto userDto = new UserDto(id, "", "mail@mail.ru");

        RequestDto dto = new RequestDto();
        dto.setId(id);
        dto.setDescription(description);
        dto.setRequester(userDto);
        dto.setCreated(LocalDateTime.now());
    }
}

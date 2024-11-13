package ru.practicum.shareit.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import ru.practicum.shareit.request.controller.ItemRequestController;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestServiceImpl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestServiceImplTest {

    @MockBean
    private RequestServiceImpl itemRequestService;

    @InjectMocks
    private ItemRequestController controller;

    @Autowired
    private ObjectMapper mapper;

    private RequestDto itemRequestDto;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        itemRequestDto = RequestDto.builder()
                .id(1L)
                .description("TestItemRequestDescription")
                .requester(null)
                .created(LocalDateTime.now())
                .build();
        pageable = PageRequest.of(0, 10, Sort.by("created").descending());
    }

    @Test
    void addItemRequest() throws Exception {
        when(itemRequestService.create(any(), eq(1L))).thenReturn(itemRequestDto);
    }

    @Test
    void getAllItemRequestByOwner() throws Exception {
        when(itemRequestService.findRequestsByUser(anyLong())).thenReturn(Collections.emptyList());
    }

}
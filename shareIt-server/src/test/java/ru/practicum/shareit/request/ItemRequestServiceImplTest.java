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
import ru.practicum.shareit.request.controller.ItemRequestController;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.service.RequestServiceImpl;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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
    void addItemRequest() {
    }

    @Test
    void getAllItemRequestByOwner()  {
    }

}
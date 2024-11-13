package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.request.service.RequestServiceImpl;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestServiceImplTest {
    private RequestServiceImpl itemRequestService;
    private RequestRepository itemRequestRepository;
    private UserRepository userRepository;
    private User user;
    private ItemRequest itemRequest;
    private RequestDto itemRequestDto;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        itemRequestRepository = mock(RequestRepository.class);

        user = User.builder()
                .id(1L)
                .name("Test")
                .email("Test@test.com")
                .build();
        when(userRepository.save(any())).thenReturn(user);

        itemRequest = ItemRequest.builder()
                .id(1L)
                .requester(user)
                .created(LocalDateTime.now())
                .description("Test")
                .build();


        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(itemRequestRepository.save(any())).thenReturn(itemRequest);
        when(itemRequestRepository.findById(any())).thenReturn(Optional.of(itemRequest));
        pageable = PageRequest.of(0, 10, Sort.by("created").descending());
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), List.of(itemRequest).size());
        final Page<ItemRequest> page = new PageImpl<>(List.of(itemRequest).subList(start, end), pageable, List.of(itemRequest).size());
        when(itemRequestRepository.findAll((Pageable) any())).thenReturn(page);
    }

    @Test
    void getAllItemRequestByOwner() {
        itemRequest.getItems();
    }

    @Test
    void addItemRequest() {
        itemRequest.getItems();
    }

    @Test
    void getItemRequestById() {
        itemRequest.getItems();
    }

    @Test
    void getAllItemRequestToOtherUser() {
        itemRequest.getItems();
    }
}
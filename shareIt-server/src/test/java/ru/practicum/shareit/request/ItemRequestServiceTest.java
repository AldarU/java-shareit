package ru.practicum.shareit.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.request.service.RequestServiceImpl;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ItemRequestServiceTest {

    @InjectMocks
    private RequestServiceImpl requestService;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private ItemRepository itemRepository;
    private final User user = new User(1L, "User", "user@email.com");
    private final UserDto userDto = new UserDto(1L, "User", "user@email.com");
    private final ItemRequest itemRequest = ItemRequest.builder()
            .id(1L)
            .requester(user)
            .description("description")
            .build();
    private final RequestDto itemRequestDto = RequestDto.builder()
            .id(1L)
            .description("description")
            .requester(userDto)
            .build();

    @Test
    void createRequestWhenUserIsExistThenReturnedExpectedRequest() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenReturn(userDto);

        Mockito.when(requestRepository.save(any()))
                .thenReturn(itemRequest);

        assertEquals(requestService.create(itemRequestDto, 1L), itemRequestDto);
    }

    @Test
    void createRequestWhenUserIsNotExistThenReturnedNotFoundException() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenThrow(new NotFoundException(String.format("User with ID = %d not found.", 1L)));

        Exception e = assertThrows(NotFoundException.class,
                () -> requestService.create(itemRequestDto, 100L));

        assertEquals(e.getMessage(), String.format("User with ID = %d not found.", 1L));
    }

    @Test
    void findByIdWhenRequestIsNotExistThenReturnedNotFoundException() {
        Mockito.when(requestRepository.findById(anyLong()))
                .thenThrow(new NotFoundException(String.format("Request with ID = %d not found.", 1L)));

        Exception e = assertThrows(NotFoundException.class,
                () -> requestService.getById(1L, 1L));

        assertEquals(e.getMessage(), String.format("Request with ID = %d not found.", 1L));
    }

    @Test
    void test() throws IOException {
    }

    @Test
    void testNull() {

    }

    @Test
    void testUpdateLaterAldar() {
    }

    @Test
    void findAllRequestsWhenParamsIsExistThenReturnedExpectedListRequests() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenReturn(userDto);
        Mockito.when(itemRepository.findAllByItemRequest(any()))
                .thenReturn(new ArrayList<>());
        Mockito.when(requestRepository.findByRequesterIdIsNot(anyLong()))
                .thenReturn(List.of(itemRequest));

        assertEquals(requestService.findRequests(1L), List.of(itemRequestDto));
    }

    @Test
    void findAllRequestsWhenUserIsNotExistThenReturnedNotFoundException() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenThrow(new NotFoundException(String.format("User with ID = %d not found.", 1L)));

        Exception e = assertThrows(NotFoundException.class,
                () -> requestService.findRequests(1L));

        assertEquals(e.getMessage(), String.format("User with ID = %d not found.", 1L));
    }

    @Test
    void findAllUserRequestsWhenUserIsExistThenReturnedExpectedListRequests() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenReturn(userDto);
        Mockito.when(requestRepository.findByRequesterIdOrderByCreatedDesc(anyLong()))
                .thenReturn(List.of(itemRequest));
        Mockito.when(itemRepository.findAllByItemRequest(any()))
                .thenReturn(new ArrayList<>());

        assertEquals(requestService.findRequestsByUser(1L), List.of(itemRequestDto));
    }

    @Test
    void findAllUserRequestsWhenUserIsNotExistThenReturnedNotFoundException() {
        Mockito.when(userService.getUserById(anyLong()))
                .thenThrow(new NotFoundException(String.format("User with ID = %d not found.", 1L)));

        Exception e = assertThrows(NotFoundException.class,
                () -> requestService.findRequestsByUser(1L));

        assertEquals(e.getMessage(), String.format("User with ID = %d not found.", 1L));
    }
}
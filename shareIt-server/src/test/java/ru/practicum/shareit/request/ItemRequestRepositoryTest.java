package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestRepositoryTest {

    private final RequestRepository repository;

    @Test
    void createItemRequest() {
        ItemRequest itemRequest = ItemRequest.builder()
                .created(LocalDateTime.now())
                .requester(new User())
                .description("description")
                .build();
    }

    @Test
    void getItemRequests() {
        ItemRequest itemRequest = ItemRequest.builder()
                .created(LocalDateTime.now())
                .requester(new User())
                .description("description")
                .build();
    }

    @Test
    void getItemRequestById() {
        ItemRequest itemRequest = ItemRequest.builder()
                .created(LocalDateTime.now())
                .requester(new User())
                .description("description")
                .build();
    }
}
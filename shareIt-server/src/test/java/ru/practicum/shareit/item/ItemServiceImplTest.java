package ru.practicum.shareit.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.comments.model.Comment;
import ru.practicum.shareit.comments.repository.CommentRepository;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RequestRepository itemRequestRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    private User user;
    private User owner;
    private Item item;
    private ItemDto itemDto;
    private Comment comment;
    private CommentDto commentDto;
    private ItemRequest itemRequest;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .name("Test User")
                .email("testuser@example.com")
                .build();

        owner = User.builder()
                .id(2L)
                .name("Owner")
                .email("owner@example.com")
                .build();

        itemRequest = ItemRequest.builder()
                .id(1L)
                .requester(user)
                .description("Request Description")
                .created(LocalDateTime.now())
                .build();

        item = Item.builder()
                .id(1L)
                .name("Test Item")
                .description("Test Description")
                .available(true)
                .ownerId(owner.getId())
                .itemRequest(itemRequest)
                .build();

        itemDto = ItemMapper.buildItemDto(item);

        comment = Comment.builder()
                .id(1L)
                .text("Great item!")
                .item(item)
                .author(user)
                .created(LocalDateTime.now())
                .build();

        commentDto = CommentDto.builder()
                .id(1L)
                .text("Comment Text")
                .authorName(owner.getName())
                .created(LocalDateTime.now())
                .build();
    }

    @Test
    void create() {
    }

    @Test
    void createWithInvalidUser() {
    }

    @Test
    void update() {
    }

    @Test
    void updateWithInvalidUser() {
    }

    @Test
    void updateWithInvalidItem() {
    }

    @Test
    void updateWithInvalidOwner() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByIdWithInvalidUser() {
    }


    @Test
    void getAll() {
    }

    @Test
    void addComment() {
    }

    @Test
    void createShouldThrowNotFoundException_WhenUserNotFound() {
    }

    @Test
    void updateShouldReturnUpdatedItem() {
    }

    @Test
    void updateShouldThrowNotFoundException_WhenUserNotFound() {
    }

    @Test
    void updateShouldThrowNotFoundException_WhenItemNotFound() {
    }

    @Test
    void getByIdShouldReturnItem() {
    }

    @Test
    void getByIdShouldThrowNotFoundExceptionWhenUserNotFound() {
    }

    @Test
    void getByIdShouldThrowNotFoundExceptionWhenItemNotFound() {
    }

    @Test
    void getAllShouldReturnItems() {
    }

    @Test
    void getAllShouldThrowNotFoundExceptionWhenUserNotFound() {
    }

    @Test
    void addCommentShouldReturnComment() {
    }

    @Test
    void addCommentShouldThrowNotFoundExceptionWhenUserNotFound() {
    }

    @Test
    void addCommentShouldThrowNotFoundExceptionWhenItemNotFound() {
    }

    @Test
    void addCommentShouldThrowValidationExceptionWhenNoBookingFound() {
    }
}
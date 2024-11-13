package ru.practicum.shareit.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {
    private UserService userService;
    private UserRepository userRepository;
    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        user = User.builder()
                .id(1L)
                .name("Test")
                .email("Email@test.com")
                .build();
        userDto = UserMapper.buildUserDto(user);

        when(userRepository.save(any())).thenReturn(user);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.findAll()).thenReturn(List.of(user));
    }

    @Test
    void create() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    }

    @Test
    void createUserAlreadyExists() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    }


    @Test
    public void getUserNotExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    }

    @Test
    void update() {
        userService.updateUser(user, user.getId());
    }

    @Test
    public void updateUserNotExist() {
        userService.updateUser(user, user.getId());
    }

    @Test
    void updateUserAlreadyExists() {
        userService.createUser(user);
    }

    @Test
    void getById() {
        UserDto result = userService.getUserById(user.getId());
    }

    @Test
    void delete() {
        userRepository.deleteById(1L);
    }

    @Test
    public void deleteUserNotExist() {
        userRepository.deleteById(1L);
    }
}
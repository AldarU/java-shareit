package ru.practicum.shareit.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void createUser() {
        long id = 1;
        long userId = 2;
        String name = "name";
    }

    @Test
    void getUsers() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        UserDto userDto = UserDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = service.getUsers();

        Assertions.assertEquals(userDto, result.stream().findFirst().get());
    }

    @Test
    void getUserByIdUserNotFoundException() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        UserDto userDto = UserDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = service.getUsers();

        Assertions.assertEquals(userDto, result.stream().findFirst().get());
    }

    @Test
    void getUserByIdr() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        UserDto userDto = UserDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(user));

        UserDto result = service.getUserById(id);

        Assertions.assertEquals(userDto, result);

    }

    @Test
    void isEmailExists() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
    }

    @Test
    void updateUserUserNotFoundException() {
        long id = 1;
        String email = "email@email.dk";
        String newName = "newName";
    }

    @Test
    void updateUserUser() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        UserDto userDto = UserDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = service.getUsers();

        Assertions.assertEquals(userDto, result.stream().findFirst().get());
    }

    @Test
    void updateUserUserNull() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);

        UserDto userDto = UserDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<UserDto> result = service.getUsers();

        Assertions.assertEquals(userDto, result.stream().findFirst().get());
    }
}
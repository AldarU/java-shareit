package ru.practicum.shareit.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Test
    void createUser() {

        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User user = new User();
        user.setName(name);
        user.setEmail(email);
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
        String email = "email@email.dk";
        String newName = "newName";

        User user = new User();
        user.setId(id);
        user.setName(newName);
        user.setEmail(email);

        UserDto userDto = UserDto.builder()
                .id(id)
                .name(newName)
                .email(email)
                .build();
    }

    @Test
    void updateUserUserNull() {
        long id = 1;
        String email = "email@email.dk";
        String newName = "newName";

        User user = new User();
        user.setId(id);


        UserDto userDto = UserDto.builder()
                .id(id)
                .build();
    }
}
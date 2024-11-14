package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserRepositoryTest {

    private final UserRepository repository;

    @Test
    void createUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
    }

    @Test
    void getUsers() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
    }

    @Test
    void isEmailExists() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setName("name");
        user.setEmail("email");
    }
}
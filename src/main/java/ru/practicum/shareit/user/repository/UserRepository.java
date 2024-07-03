package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User updateUser(User user, Long id);

    List<User> getUsers();

    User getUserById(Long id);

    User deleteUser(Long userId);

}

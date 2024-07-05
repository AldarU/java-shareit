package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userServiceImpl.createUser(user);
    }

    @PatchMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userServiceImpl.updateUser(user, id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userServiceImpl.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }
}

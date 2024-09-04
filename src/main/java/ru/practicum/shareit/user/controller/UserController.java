package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @PostMapping
    public UserDto createUser(@Valid @RequestBody User user) {
        log.info("Create user");
        return userServiceImpl.createUser(user);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@RequestBody User user, @PathVariable Long id) {
        log.info("Update user");
        return userServiceImpl.updateUser(user, id);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        log.info("Get users");
        return userServiceImpl.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        log.info("Get user by id {}", id);
        return userServiceImpl.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("Delete user by id {}", id);
        userServiceImpl.deleteUser(id);
    }
}

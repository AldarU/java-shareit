package ru.practicum.shareit.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.user.controller.UserController;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(UserController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class UserControllerTest {

    @MockBean
    private final UserServiceImpl service;
    private final ObjectMapper mapper;
    private final MockMvc mockMvc;

    @Test
    @SneakyThrows
    void createUser() {
        String name = "name";
        String email = "email@mail.ru";
        User user = new User(1L, name, email);

        mockMvc
                .perform(post("/users")
                                .header("X-Sharer-User-Id", "1")
                                .content(mapper.writeValueAsString(user))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateUser() {
        String name = "name";
        String email = "email@mail.ru";
        User user = new User(1L, name, email);

        mockMvc
                .perform(
                        patch("/users/" + user.getId())
                                .content(mapper.writeValueAsString(user))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUs() throws Exception {
        long id = 1;
        Mockito.doNothing().when(service).deleteUser(id);

        mockMvc
                .perform(
                        delete("/users/1")
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getUsers() {
        long id = 1;
        String name = "name";
        String email = "email@email.dk";
        User userDtoCreate = User.builder()
                .name(name)
                .email(email)
                .build();

        mockMvc
                .perform(
                        get("/users")
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @SneakyThrows
    void getUserById() {
        long id = 1;
        long userId = 2;
        String name = "name";
        String email = "email@email.dk";

        User userDto = User.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();

        mockMvc
                .perform(
                        get("/users/" + id)
                                .header("X-Sharer-User-Id", userId)
                                .characterEncoding(StandardCharsets.UTF_8)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
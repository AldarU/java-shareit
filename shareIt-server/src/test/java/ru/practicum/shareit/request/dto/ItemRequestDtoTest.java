package ru.practicum.shareit.request.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestDtoTest {
    private final JacksonTester<RequestDto> json;

    @Test
    void testSerialize() throws Exception {
    }

    @Test
    void getAll() throws Exception {
    }

    @Test
    void getById() throws Exception {
    }

    @Test
    void update() throws Exception {
    }

    @Test
    void create() throws Exception {
    }

    @Test
    void delete() throws Exception {
    }
}
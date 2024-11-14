package ru.practicum.shareit.item.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.user.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class CommentDtoTest {

    private final JacksonTester<ShortBookingDto> json;

    @Test
    public void commentDto() {
        UserDto user = UserDto.builder().name("authorName").build();

        CommentDto dto = CommentDto.builder()
                .authorName(user.getName())
                .build();

        assertThat(dto.getAuthorName()).isEqualTo(user.getName());

    }

}
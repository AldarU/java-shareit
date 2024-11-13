package ru.practicum.shareit.booking.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookingDtoTest {
    private final JacksonTester<BookingDto> json;

    @Test
    void testSerialize() throws Exception {
        UserDto userNameDto = UserDto.builder()
                .id(1L)
                .name("Test")
                .build();
        ItemDto itemNameDto = ItemDto.builder()
                .id(1L)
                .name("Test")
                .build();
        BookingDto bookingRequestDto = BookingDto.builder()
                .id(1L)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(1))
                .booker(userNameDto)
                .item(itemNameDto)
                .status(Status.APPROVED)
                .build();

        JsonContent<BookingDto> result = json.write(bookingRequestDto);

        assertThat(result).hasJsonPath("$.id")
                .hasJsonPath("$.start")
                .hasJsonPath("$.end")
                .hasJsonPath("$.status")
                .hasJsonPath("$.booker.id")
                .hasJsonPath("$.booker.name")
                .hasJsonPath("$.item.id")
                .hasJsonPath("$.item.name");

        assertThat(result).extractingJsonPathNumberValue("$.id")
                .satisfies(item_id -> assertThat(item_id.longValue()).isEqualTo(bookingRequestDto.getId()));
        assertThat(result).extractingJsonPathStringValue("$.start")
                .satisfies(created -> assertThat(created).isNotNull());
        assertThat(result).extractingJsonPathStringValue("$.end")
                .satisfies(created -> assertThat(created).isNotNull());
        assertThat(result).extractingJsonPathStringValue("$.status")
                .satisfies(status -> assertThat(Status.valueOf(status)).isEqualTo(bookingRequestDto.getStatus()));

        assertThat(result).extractingJsonPathNumberValue("$.booker.id")
                .satisfies(booker_id -> assertThat(booker_id.longValue()).isEqualTo(bookingRequestDto.getBooker().getId()));
        assertThat(result).extractingJsonPathStringValue("$.booker.name")
                .satisfies(booker_name -> assertThat(booker_name).isEqualTo(bookingRequestDto.getBooker().getName()));

        assertThat(result).extractingJsonPathNumberValue("$.item.id")
                .satisfies(id -> assertThat(id.longValue()).isEqualTo(bookingRequestDto.getItem().getId()));
        assertThat(result).extractingJsonPathStringValue("$.item.name")
                .satisfies(item_name -> assertThat(item_name).isEqualTo(bookingRequestDto.getItem().getName()));
    }
}
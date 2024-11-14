package ru.practicum.shareit.booking.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookingCreateDtoTest {
    private final JacksonTester<ShortBookingDto> json;

    @Test
    public void bookingCreateDtoTest() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime bef = LocalDateTime.now().minusDays(3);
        ShortBookingDto dto = new ShortBookingDto(
                1L,
                now,
                bef
        );

        JsonContent<ShortBookingDto> result = json.write(dto);

        assertThat(result).extractingJsonPathNumberValue("$.itemId").isEqualTo(1);
    }
}
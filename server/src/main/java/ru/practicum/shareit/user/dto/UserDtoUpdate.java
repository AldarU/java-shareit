package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@Builder
public class UserDtoUpdate {
    private String name;
    private String email;
}

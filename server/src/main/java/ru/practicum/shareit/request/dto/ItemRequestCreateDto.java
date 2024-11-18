package ru.practicum.shareit.request.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class ItemRequestCreateDto {
    private String description;
}

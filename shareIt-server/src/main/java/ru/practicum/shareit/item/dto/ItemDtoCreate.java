package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class ItemDtoCreate {
    private String name;
    private String description;
    private Boolean available;
    private Long requestId;
}

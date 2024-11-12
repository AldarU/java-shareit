package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemRequestDtoTest {
    private final JacksonTester<RequestDto> json;

    @Test
    void testSerialize() throws Exception {
        User user = User.builder()
                .id(1L)
                .name("Test")
                .email("Test@test.com")
                .build();
        ItemRequest itemRequest = ItemRequest.builder()
                .id(1L)
                .requester(user)
                .created(LocalDateTime.now())
                .description("TestItemRequestDescription")
                .build();
        Item item = Item.builder()
                .id(1L)
                .name("Test")
                .description("Test")
                .itemRequest(itemRequest)
                .available(true)
                .ownerId(user.getId())
                .build();
        itemRequest.setItems(List.of(item));
        RequestDto itemRequestDto = RequestMapper.buildItemRequestDto(itemRequest);

        JsonContent<RequestDto> result = json.write(itemRequestDto);

        assertThat(result).extractingJsonPathNumberValue("$.id")
                .satisfies(id -> assertThat(id.longValue()).isEqualTo(itemRequestDto.getId()));
        assertThat(result).extractingJsonPathStringValue("$.created")
                .satisfies(created -> assertThat(created).isNotNull());
        assertThat(result).extractingJsonPathNumberValue("$.requester.id")
                .satisfies(requester_id -> assertThat(requester_id.longValue()).isEqualTo(itemRequestDto.getId()));
        assertThat(result).extractingJsonPathStringValue("$.requester.name")
                .satisfies(requester_name -> assertThat(requester_name).isEqualTo(itemRequestDto.getRequester().getName()));
        assertThat(result).extractingJsonPathStringValue("$.requester.email")
                .satisfies(requester_email -> assertThat(requester_email).isEqualTo(itemRequestDto.getRequester().getEmail()));
        assertThat(result).extractingJsonPathNumberValue("$.items[0].id");
    }
}
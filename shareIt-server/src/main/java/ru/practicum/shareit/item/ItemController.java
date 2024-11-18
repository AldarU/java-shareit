package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.user.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDtoCreate itemDto,
                                              @NotNull @RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        userService.getUserById(userId);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Sharer-User-Id", userId.toString());
        return new ResponseEntity<ItemDto>(itemService.createItem(itemDto, userId), responseHeaders, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ItemDto updateItem(@PathVariable long id, @RequestBody ItemDtoUpdate itemDto,
                              @RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        userService.getUserById(userId);
        return itemService.updateItem(id, itemDto);
    }

    @GetMapping
    public List<ItemDto> getItems(@RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        return itemService.getItems(userId);
    }

    @GetMapping("/{id}")
    public ItemDto getItemById(@PathVariable long id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/search")
    public List<ItemDto> searchItem(@RequestParam("text") String text,
                                    @RequestHeader(name = "X-Sharer-User-Id") Long userId) {
        userService.getUserById(userId);
        return itemService.searchItem(text);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(long id) {
        itemService.deleteItem(id);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@RequestHeader(name = "X-Sharer-User-Id") Long userId,
                                    @PathVariable Long itemId, @RequestBody CommentCreateDto commentText) {
        return itemService.createComment(commentText, userId, itemId);
    }
}

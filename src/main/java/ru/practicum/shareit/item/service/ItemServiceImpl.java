package ru.practicum.shareit.item.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserService userService;

    @Override
    public ItemDto createItem(ItemDto item, Long ownerId) {
        if (userService.getUserById(ownerId) != null) {
            return itemMapper.buildItemDto(itemRepository.createItem(itemMapper.buildItem(item), ownerId));
        } else {
            if (!item.getAvailable()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Available null");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User не найден");
        }
    }

    @Override
    public ItemDto updateItem(ItemDto item, Long id, Long ownerId) {
        if (item.getName() == null) {
            item.setName(itemRepository.getItemById(id).getName());
        }
        if (item.getDescription() == null) {
            item.setDescription(itemRepository.getItemById(id).getDescription());
        }
        if (item.getAvailable() == null) {
            item.setAvailable(itemRepository.getItemById(id).getAvailable());
        }

        item.setId(id);
        item.setOwnerId(ownerId);

        if (userService.getUserById(ownerId) != null) {
            Long oldOwnerItem = itemRepository.getItemById(id).getOwnerId();
            if (oldOwnerItem.equals(ownerId)) {
                return itemMapper.buildItemDto(itemRepository.updateItem(itemMapper.buildItem(item), id, ownerId));
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Can not update with other owner");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User не найден");
        }
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        return itemMapper.buildItemDto(itemRepository.getItemById(itemId));
    }

    @Override
    public List<ItemDto> getOwnerItems(Long ownerId) {
        return itemRepository
                .getOwnerItems(ownerId)
                .stream()
                .map(itemMapper::buildItemDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getItemsByName(String text) {
        if (text.isBlank() || text == null) {
            return new ArrayList<>();
        }

        return itemRepository
                .getItemsByName(text)
                .stream()
                .map(itemMapper::buildItemDto)
                .collect(Collectors.toList());
    }
}

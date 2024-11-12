package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.request.dto.RequestDto;
import ru.practicum.shareit.request.dto.RequestDtoWithItems;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserServiceImpl userService;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public RequestDto create(RequestDto requestDto, Long userId) {

        ItemRequest itemRequest = ItemRequest.builder()
                .description(requestDto.getDescription())
                .requester(UserMapper.buildUser(userService.getUserById(userId)))
                .created(LocalDateTime.now())
                .build();

        return RequestMapper.buildItemRequestDto(requestRepository.save(itemRequest));
    }

    @Override
    @Transactional
    public RequestDtoWithItems getById(Long userId, Long requestId) {
        ItemRequest itemRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Request is not found"));
        itemRequest.setItems(itemRepository.findAllByItemRequest(itemRequest));

        RequestDtoWithItems itemRequestDto = RequestMapper.buildItemRequestDtoWithItems(itemRequest);
        itemRequestDto.setRequester(userService.getUserById(userId));

        return itemRequestDto;
    }

    @Override
    @Transactional
    public List<RequestDto> findRequests(Long userId) {
        UserMapper.buildUser(userService.getUserById(userId));
        List<RequestDto> list = new ArrayList<>();
        List<ItemRequest> findAllByRequesterIdIsNot = requestRepository.findByRequesterIdIsNot(userId);
        findAllByRequesterIdIsNot.forEach(itemRequest -> {
            itemRequest.setItems(itemRepository.findAllByItemRequest(itemRequest));
            RequestDto requestDto = RequestMapper.buildItemRequestDto(itemRequest);
            list.add(requestDto);
        });
        return list;
    }

    @Override
    @Transactional
    public List<RequestDto> findRequestsByUser(Long userId) {
        userService.getUserById(userId);
        List<RequestDto> list = new ArrayList<>();
        List<ItemRequest> findAllByRequesterIdOrderByCreatedDesc = requestRepository.findByRequesterIdOrderByCreatedDesc(userId);
        findAllByRequesterIdOrderByCreatedDesc.forEach(item -> {
            item.setItems(itemRepository.findAllByItemRequest(item));
            RequestDto itemRequestDto = RequestMapper.buildItemRequestDto(item);
            list.add(itemRequestDto);
        });
        return list;
    }
}

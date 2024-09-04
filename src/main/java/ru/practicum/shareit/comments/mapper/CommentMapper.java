package ru.practicum.shareit.comments.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.comments.dto.CommentDto;
import ru.practicum.shareit.comments.model.Comment;
import ru.practicum.shareit.item.mapper.ItemMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .item(ItemMapper.buildItemDto(comment.getItem()))
                .authorName(comment.getAuthor().getName())
                .created(comment.getCreated())
                .build();
    }

    public static Comment buildComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .text(commentDto.getText())
                .created(commentDto.getCreated())
                .build();
    }

    public static List<CommentDto> toDto(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }
}

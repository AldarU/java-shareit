package ru.practicum.shareit.exceptions;

public class AlreadyException extends RuntimeException {
    public AlreadyException(String message) {
        super(message);
    }
}

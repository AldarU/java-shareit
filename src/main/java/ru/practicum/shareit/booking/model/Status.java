package ru.practicum.shareit.booking.model;

public enum Status {
    WAITING, // новая бронь, ожидаем подтв-е
    APPROVED, // бронь подтв-а
    REJECTED, // бронь откл-а
    CANCELED // бронь отмен-а созд-м
}

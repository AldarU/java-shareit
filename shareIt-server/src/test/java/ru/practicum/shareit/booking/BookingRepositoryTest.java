package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.time.LocalDateTime;

@DataJpaTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookingRepositoryTest {
    @Test
    void createBooking() {
        Booking booking = Booking.builder()
                .status(Status.WAITING)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(6))
                .item(new Item())
                .booker(new User())
                .build();
    }

    @Test
    void updateBooking() {
        Booking booking = Booking.builder()
                .status(Status.WAITING)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(6))
                .item(new Item())
                .booker(new User())
                .build();
    }

    @Test
    void findAll() {
        Item item = new Item();
        item.setName("name");
        User user = new User();
        user.setName("name");
        user.setEmail("email@email.dk");
        Booking booking = Booking.builder()
                .status(Status.WAITING)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(6))
                .item(new Item())
                .booker(new User())
                .build();
    }


    @Test
    void findById() {
        Booking booking = Booking.builder()
                .status(Status.WAITING)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusDays(6))
                .item(new Item())
                .booker(new User())
                .build();
    }
}
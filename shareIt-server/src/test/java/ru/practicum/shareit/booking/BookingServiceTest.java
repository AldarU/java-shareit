package ru.practicum.shareit.booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Test
    void findByIdBookingNotFoundException() {
    }

    @Test
    void updateBookingBookingNull() {
    }

    @Test
    void updateBooking() {
    }

    @Test
    void testUpdateBooking() {
        Item item = new Item();
        item.setId(1L);
        item.setName("name");
        User user = new User();
        user.setId(2L);
        user.setName("name");
        user.setEmail("email@email.dk");
    }

    @Test
    void testUpdateBookingNotApproval() {
        Item item = new Item();
        item.setId(1L);
        item.setName("name");
        User user = new User();
        user.setId(2L);
        user.setName("name");
        user.setEmail("email@email.dk");
    }

    @Test
    void createBooking() {
    }

    @Test
    void testCreateBooking() {
    }

    @Test
    void testCreateBookingNotAvaliable() {
    }

    @Test
    void testCreateBookingAvaliable() {

    }

    @Test
    void findAll() {
    }

    @Test
    void findByOwnerException() {
    }

    @Test
    void findByOwner() {

    }

    @Test
    void findByIdBookingAccessException() {
    }

    @Test
    void findById() {
    }
}

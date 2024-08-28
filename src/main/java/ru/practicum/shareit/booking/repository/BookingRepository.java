package ru.practicum.shareit.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Modifying
    @Query("UPDATE Booking b "
            + "SET b.status = :status  "
            + "WHERE b.id = :bookingId ")
    void save(Long  bookingId, Status status);

    //*******************************************************//

    List<Booking> findByBookerIdOrderByStartDesc(Long id);

    List<Booking> findByBookerIdAndStatusIsOrderByStartDesc(Long id, Status status);

    List<Booking> findByBookerIdAndEndIsAfterAndStartIsBeforeOrderByStartDesc(Long id, LocalDateTime end, LocalDateTime start);

    List<Booking> findByBookerIdAndEndIsBeforeOrderByStartDesc(Long id, LocalDateTime time);

    List<Booking> findByBookerIdAndStartIsAfterOrderByStartDesc(Long id, LocalDateTime time);

    List<Booking> findByBookerIdAndStartIsAfterAndStatusIsOrderByStartDesc(Long bookerId, LocalDateTime start, Status status);

    //*******************************************************//

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.ownerId = :ownerId "
            + "ORDER BY b.start DESC")
    List<Booking> findByItemOwnerId(Long ownerId);

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.ownerId = :ownerId "
            + "AND :time between b.start AND b.end "
            + "ORDER BY b.start DESC")
    List<Booking> findCurrentBookingsOwner(Long ownerId, LocalDateTime time);

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.ownerId = :ownerId "
            + "AND b.end < :time "
            + "ORDER BY b.start DESC")
    List<Booking> findPastBookingsOwner(Long ownerId, LocalDateTime time);

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.ownerId = :ownerId "
            + "AND b.start > :time "
            + "ORDER BY b.start DESC")
    List<Booking> findFutureBookingsOwner(Long ownerId, LocalDateTime time);

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.ownerId = :ownerId "
            + "AND b.start > :time AND b.status = :status "
            + "ORDER BY b.start DESC")
    List<Booking> findWaitingBookingsOwner(Long ownerId, LocalDateTime time, Status status);

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.ownerId = :ownerId "
            + "AND b.status = :status "
            + "ORDER BY b.start DESC")
    List<Booking> findRejectedBookingsOwner(Long ownerId, Status status);

    @Query("SELECT b FROM Booking b "
            + "INNER JOIN Item i ON b.item.id = i.id "
            + "WHERE i.id = :itemId "
            + "ORDER BY b.start DESC")
    List<Booking> findBookingsItem(Long itemId);

    List<Booking> findByItemIdAndBookerIdAndStatusIsAndEndIsBefore(Long itemId, Long bookerId, Status status, LocalDateTime time);
}

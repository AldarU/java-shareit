package ru.practicum.shareit.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookingController.class)
class BookingControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BookingService bookingService;

    @Autowired
    private MockMvc mvc;

    private final ItemDto itemDto = ItemDto.builder()
            .name("testItem")
            .description("testDescription")
            .available(true)
            .build();
    private final ShortBookingDto shortBookingDto = ShortBookingDto.builder()
            .start(LocalDateTime.of(2023, 06, 01, 15, 00, 00))
            .end(LocalDateTime.of(2023, 06, 10, 15, 00, 00))
            .itemId(1L).build();
    private final BookingDto bookingDto = BookingDto.builder()
            .start(LocalDateTime.of(2023, 06, 01, 15, 00, 00))
            .end(LocalDateTime.of(2023, 06, 10, 15, 00, 00))
            .item(itemDto)
            .build();

    @Test
    void createBookingWhenAllParamsIsValidThenReturnedStatusIsOk() throws Exception {
        Mockito.when(bookingService.createBooking(any(), anyLong()))
                .thenReturn(bookingDto);

        mvc.perform(post("/bookings")
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(shortBookingDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(bookingService).createBooking(shortBookingDto, 1L);
    }

    @Test
    void createBookingWhenStartIsNotValidThenReturnedStatusIsBadRequest() throws Exception {
        ShortBookingDto badShortBookingDto = ShortBookingDto.builder()
                .start(LocalDateTime.of(1000, 05, 10, 13, 00, 00))
                .end(LocalDateTime.of(2023, 05, 20, 13, 00, 00))
                .itemId(1L)
                .build();

        mvc.perform(post("/bookings")
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(badShortBookingDto)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(bookingService, never()).createBooking(badShortBookingDto, 1L);
    }

    @Test
    void createBookingWhenUserIsNotOwnerThenReturnedStatusIsNotFound() throws Exception {
        Mockito.when(bookingService.createBooking(any(), anyLong()))
                .thenThrow(new NotFoundException(String.format("User with ID = %d not found.", 100L)));

        mvc.perform(post("/bookings")
                        .header("X-Sharer-User-Id", 100L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(shortBookingDto)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void findBookingByIdWhenAllParamsIsValidThenReturnedStatusIsOk() throws Exception {
        Mockito.when(bookingService.findBookingById(anyLong(), anyLong()))
                .thenReturn(bookingDto);

        mvc.perform(get("/bookings/{bookingId}", 1L)
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(shortBookingDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(bookingService).findBookingById(1L, 1L);
    }

    @Test
    void findBookingByIdWhenBookingIdNotFoundThenReturnedStatusIsNotFound() throws Exception {
        Mockito.when(bookingService.findBookingById(anyLong(), anyLong()))
                .thenThrow(new NotFoundException(String.format("Booking with ID = %d not found.", 100L)));

        mvc.perform(get("/bookings/{bookingId}", 100L)
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(shortBookingDto)))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    void findAllByUserIdWhenAllParamsIsValidThenReturnedStatusIsOk() throws Exception {
        Mockito.when(bookingService.findBookingsByUser(any(), anyLong()))
                .thenReturn(List.of(bookingDto));

        String result = mvc.perform(get("/bookings/?state=ALL")
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(result, mapper.writeValueAsString(List.of(bookingDto)));
    }

    @Test
    void findAllByOwnerIdWhenAllParamsIsValidThenReturnedStatusIsOk() throws Exception {
        Mockito.when(bookingService.findBookingsByOwner(any(), anyLong()))
                .thenReturn(List.of(bookingDto));

        String result = mvc.perform(get("/bookings/owner?state=ALL")
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(result, mapper.writeValueAsString(List.of(bookingDto)));
    }

    @Test
    void approveBookingWhenAllParamsIsValidThenReturnedStatusIsOk() throws Exception {
        Mockito.when(bookingService.approveBooking(anyLong(), anyInt(), anyBoolean()))
                .thenReturn(bookingDto);

        mvc.perform(get("/bookings/1?approved=true")
                        .header("X-Sharer-User-Id", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
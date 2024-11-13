package ru.practicum.shareit.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.booking.controller.BookingController;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.ShortBookingDto;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.exception.NotFoundException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
class BookingControllerTest {

    @MockBean
    private BookingService bookingService;

    @InjectMocks
    private BookingController controller;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;
    private ShortBookingDto bookingCreateDto;
    private BookingDto bookingRequestDto;

    @BeforeEach
    void setUp() {
        bookingCreateDto = ShortBookingDto.builder()
                .itemId(1L)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusHours(1))
                .build();

        bookingRequestDto = BookingDto.builder()
                .id(1L)
                .start(LocalDateTime.now())
                .end(LocalDateTime.now().plusHours(1))
                .build();
    }

    @Test
    void addBooking() throws Exception {
        when(bookingService.createBooking(any(), anyLong())).thenReturn(bookingRequestDto);

        mvc.perform(post("/bookings")
                        .header("X-Sharer-User-Id", 1L)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookingCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookingRequestDto.getId()), Long.class))
                .andExpect(jsonPath("$.start", is(bookingRequestDto.getStart().format(DateTimeFormatter.ISO_DATE_TIME))))
                .andExpect(jsonPath("$.end", is(bookingRequestDto.getEnd().format(DateTimeFormatter.ISO_DATE_TIME))));
        verify(bookingService, times(1)).createBooking(any(), anyLong());
    }

    @Test
    void getAllBookingsByUser() throws Exception {
        when(bookingService.findBookingsByOwner(anyString(), anyLong())).thenReturn(Collections.emptyList());
    }

    @Test
    void getAllBookingsAllItemsByOwner() throws Exception {
        when(bookingService.findBookingsByOwner(anyString(), anyLong())).thenReturn(Collections.emptyList());

        mvc.perform(get("/bookings/owner")
                        .header("X-Sharer-User-Id", 1L)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        verify(bookingService, times(1)).findBookingsByOwner(anyString(), anyLong());
    }

    @Test
    void approveOrRejectBooking() throws Exception {
        when(bookingService.approveBooking(anyLong(), anyLong(), anyBoolean())).thenReturn(bookingRequestDto);

        mvc.perform(patch("/bookings/1?approved=true")
                        .header("X-Sharer-User-Id", 1L)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookingRequestDto.getId()), Long.class))
                .andExpect(jsonPath("$.start", is(bookingRequestDto.getStart().format(DateTimeFormatter.ISO_DATE_TIME))))
                .andExpect(jsonPath("$.end", is(bookingRequestDto.getEnd().format(DateTimeFormatter.ISO_DATE_TIME))));
        verify(bookingService, times(1)).approveBooking(anyLong(), anyLong(), anyBoolean());
    }

    @Test
    void getById() throws Exception {
        when(bookingService.findBookingById(anyLong(), anyLong())).thenReturn(bookingRequestDto);

        mvc.perform(get("/bookings/1")
                        .header("X-Sharer-User-Id", 1L)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(bookingRequestDto.getId()), Long.class))
                .andExpect(jsonPath("$.start", is(bookingRequestDto.getStart().format(DateTimeFormatter.ISO_DATE_TIME))))
                .andExpect(jsonPath("$.end", is(bookingRequestDto.getEnd().format(DateTimeFormatter.ISO_DATE_TIME))));
        verify(bookingService, times(1)).findBookingById(anyLong(), anyLong());
    }

    @Test
    void getAllBookingsByUserNotFound() throws Exception {
        when(bookingService.findBookingsByUser(anyString(), anyLong())).thenThrow(new NotFoundException("Пользователя нет с таким id = " + 1L));

        mvc.perform(get("/bookings")
                        .header("X-Sharer-User-Id", 1L)
                        .param("state", "ALL"))
                .andExpect(status().isNotFound());
    }
}
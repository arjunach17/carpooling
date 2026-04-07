package com.carpooling.controller;

import com.carpooling.dto.BookingRequest;
import com.carpooling.dto.BookingResponse;
import com.carpooling.model.Booking;
import com.carpooling.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    public ResponseEntity<BookingResponse> bookRide(@Valid @RequestBody BookingRequest request) {
        Booking booking = bookingService.bookRide(
                request.getRideId(),
                request.getPassengerId(),
                request.getSeatsBooked()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(booking));
    }


    @GetMapping("/ride/{rideId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByRide(@PathVariable Long rideId) {
        List<BookingResponse> bookings = bookingService.getBookingsByRide(rideId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookings);
    }


    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByPassenger(@PathVariable Long passengerId) {
        List<BookingResponse> bookings = bookingService.getBookingsByPassenger(passengerId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookings);
    }


    private BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .rideId(booking.getRide().getId())
                .rideSource(booking.getRide().getSource())
                .rideDestination(booking.getRide().getDestination())
                .passengerId(booking.getPassenger().getId())
                .passengerName(booking.getPassenger().getName())
                .seatsBooked(booking.getSeatsBooked())
                .build();
    }
}

package com.carpooling.controller;

import com.carpooling.dto.RideRequest;
import com.carpooling.dto.RideResponse;
import com.carpooling.dto.UserResponse;
import com.carpooling.model.Ride;
import com.carpooling.repository.BookingRepository;
import com.carpooling.service.RideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;
    private final BookingRepository bookingRepository; // to compute availableSeats


    @PostMapping
    public ResponseEntity<RideResponse> createRide(@Valid @RequestBody RideRequest request) {
        Ride ride = Ride.builder()
                .source(request.getSource())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .totalSeats(request.getTotalSeats())
                .build();

        Ride saved = rideService.createRide(ride, request.getDriverId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }


    @GetMapping
    public ResponseEntity<List<RideResponse>> getAllRides() {
        List<RideResponse> rides = rideService.getAllRides()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rides);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RideResponse> getRideById(@PathVariable Long id) {
        return ResponseEntity.ok(toResponse(rideService.getRideById(id)));
    }


    @GetMapping("/search")
    public ResponseEntity<List<RideResponse>> searchRides(
            @RequestParam String source,
            @RequestParam String destination) {

        List<RideResponse> rides = rideService.getRidesBySourceAndDestination(source, destination)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rides);
    }


    private RideResponse toResponse(Ride ride) {
        int booked = bookingRepository.calculateBookedSeats(ride);
        return RideResponse.builder()
                .id(ride.getId())
                .source(ride.getSource())
                .destination(ride.getDestination())
                .departureTime(ride.getDepartureTime())
                .totalSeats(ride.getTotalSeats())
                .availableSeats(ride.getTotalSeats() - booked)
                .driver(UserResponse.builder()
                        .id(ride.getDriver().getId())
                        .name(ride.getDriver().getName())
                        .email(ride.getDriver().getEmail())
                        .phone(ride.getDriver().getPhone())
                        .build())
                .build();
    }
}

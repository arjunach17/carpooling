package com.carpooling.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingRequest {

    @NotNull(message = "Ride ID is required")
    private Long rideId;

    @NotNull(message = "Passenger ID is required")
    private Long passengerId;

    @Min(value = 1, message = "Must book at least 1 seat")
    private int seatsBooked;
}

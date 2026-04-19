package com.carpooling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RideRequest {

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Departure time is required")
    @Future(message = "Departure time must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureTime;

    @Min(value = 1, message = "Total seats must be at least 1")
    private int totalSeats;

    @NotNull(message = "Driver ID is required")
    private Long driverId;
}

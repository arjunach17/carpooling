package com.carpooling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RideResponse {
    private Long id;
    private String source;
    private String destination;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureTime;

    private int totalSeats;
    private int availableSeats;   // computed: totalSeats − already booked
    private UserResponse driver;
}

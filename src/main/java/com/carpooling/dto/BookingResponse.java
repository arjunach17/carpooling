package com.carpooling.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingResponse {
    private Long id;
    private Long rideId;
    private String rideSource;
    private String rideDestination;
    private Long passengerId;
    private String passengerName;
    private int seatsBooked;
}

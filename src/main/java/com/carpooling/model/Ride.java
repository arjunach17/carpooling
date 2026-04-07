package com.carpooling.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Source is required")
    @Column(nullable = false, length = 150)
    private String source;

    @NotBlank(message = "Destination is required")
    @Column(nullable = false, length = 150)
    private String destination;

    @NotNull(message = "Departure time is required")
    @Future(message = "Departure time must be in the future")
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Min(value = 1, message = "Total seats must be at least 1")
    @Column(nullable = false)
    private int totalSeats;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;
}

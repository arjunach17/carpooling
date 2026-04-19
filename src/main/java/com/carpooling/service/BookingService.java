package com.carpooling.service;

import com.carpooling.exception.BookingException;
import com.carpooling.exception.ResourceNotFoundException;
import com.carpooling.model.Booking;
import com.carpooling.model.Ride;
import com.carpooling.model.User;
import com.carpooling.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RideService rideService;
    private final UserService userService;

    @Transactional
    public Booking bookRide(Long rideId, Long passengerId, int seatsBooked) {


        Ride ride = rideService.getRideById(rideId);           // 404 if not found
        User passenger = userService.getUserById(passengerId); // 404 if not found


        int alreadyBooked = bookingRepository.calculateBookedSeats(ride);


        int remainingSeats = ride.getTotalSeats() - alreadyBooked;


        if (seatsBooked > remainingSeats) {
            throw new BookingException(
                    String.format(
                            "Not enough seats available. Requested: %d, Available: %d",
                            seatsBooked, remainingSeats
                    )
            );
        }


        Booking booking = Booking.builder()
                .ride(ride)
                .passenger(passenger)
                .seatsBooked(seatsBooked)
                .build();

        return bookingRepository.save(booking);
    }


    @Transactional(readOnly = true)
    public List<Booking> getBookingsByRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        return bookingRepository.findByRide(ride);
    }


    @Transactional(readOnly = true)
    public List<Booking> getBookingsByPassenger(Long passengerId) {
        User passenger = userService.getUserById(passengerId);
        return bookingRepository.findByPassenger(passenger);
    }


    @Transactional(readOnly = true)
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }
}

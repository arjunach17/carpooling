package com.carpooling.service;

import com.carpooling.exception.ResourceNotFoundException;
import com.carpooling.model.Ride;
import com.carpooling.model.User;
import com.carpooling.repository.RideRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RideService {

    private final RideRepository rideRepository;
    private final UserService userService;   // reuse existing user-lookup logic


    @Transactional
    public Ride createRide(Ride ride, Long driverId) {
        User driver = userService.getUserById(driverId);  // throws 404 if not found
        ride.setDriver(driver);
        return rideRepository.save(ride);
    }


    @Transactional(readOnly = true)
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found with id: " + id));
    }


    @Transactional(readOnly = true)
    public List<Ride> getRidesBySourceAndDestination(String source, String destination) {
        return rideRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination);
    }
}

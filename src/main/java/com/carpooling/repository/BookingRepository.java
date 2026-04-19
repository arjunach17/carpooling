package com.carpooling.repository;

import com.carpooling.model.Booking;
import com.carpooling.model.Ride;
import com.carpooling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


    @Query("SELECT COALESCE(SUM(b.seatsBooked), 0) FROM Booking b WHERE b.ride = :ride")
    int calculateBookedSeats(@Param("ride") Ride ride);


    List<Booking> findByRide(Ride ride);


    List<Booking> findByPassenger(User passenger);
}

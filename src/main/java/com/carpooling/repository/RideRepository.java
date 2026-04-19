package com.carpooling.repository;

import com.carpooling.model.Ride;
import com.carpooling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);

    List<Ride> findByDriver(User driver);
}

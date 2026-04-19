package com.carpooling.service;

import com.carpooling.exception.BookingException;
import com.carpooling.exception.ResourceNotFoundException;
import com.carpooling.model.User;
import com.carpooling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BookingException("A user with email '" + user.getEmail() + "' already exists.");
        }
        return userRepository.save(user);
    }


    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}

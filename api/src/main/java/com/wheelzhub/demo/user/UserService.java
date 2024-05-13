package com.wheelzhub.demo.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setId(0L); // Forces creation of a new user.

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));

        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        return user;
    }

    public User updateUser(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + user.getId() + " not found."));

        User savedUser = userRepository.save(user);

        return savedUser;
    }

    // Additional requests

    // Find all users that rented vehicle with vehicleId.
    public List<User> getUsersByVehicleId(Long vehicleId) {
        return userRepository.findAllUsersByVehicleId(vehicleId);
    }

    // Login user
    public User loginUser(User loginUser) {
        User user = userRepository.findUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword())
                .orElseThrow(() -> new EntityNotFoundException("User with username " + loginUser.getUsername() + " not found."));

        return user;
    }
}
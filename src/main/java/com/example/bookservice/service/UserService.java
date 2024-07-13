package com.example.bookservice.service;

import com.example.bookservice.common.entities.User;
import com.example.bookservice.exception.ResourceAlreadyExistsException;
import com.example.bookservice.exception.ResourceNotFoundException;
import com.example.bookservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public User read(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
    }

    public User updateUser(int id, User userDetails) {
        User user = read(id);

        user.setUserName(userDetails.getUserName());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        User user = read(id);
        userRepository.delete(user);
    }
}

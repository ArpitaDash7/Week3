package com.example.demo.user;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Customer> getAllUsers() {
        return userRepository.findAll();
    }

    public Customer createOrUpdateUser(Customer user) {
        return userRepository.save(user);
    }

    public Customer getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public Customer updateUserPassword(int id,Customer user)
    {
    	Customer user1 = userRepository.findById(id).orElse(null);
        if (user1 != null) {
            user1.setPassword(user.getPassword());
            user1.setEmail(user.getEmail());
            return userRepository.save(user1);
        }
        return null;
    }
    public void deleteUserById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}


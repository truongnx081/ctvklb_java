package com.example.zipkin;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getAllUsers() {
        // Simulate a service method that we want to trace
        return "List of all users";
    }
}
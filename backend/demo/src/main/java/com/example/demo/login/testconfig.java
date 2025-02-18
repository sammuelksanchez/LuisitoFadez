package com.example.demo.login;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

@Service
public class testconfig {

    public void testFirebaseInitialization() {
        try {
            // Attempt to get a user (optional, you can test with a valid user ID)
            UserRecord user = FirebaseAuth.getInstance().getUserByPhoneNumber("+1234567890");
            System.out.println("User found: " + user.getPhoneNumber());
        } catch (Exception e) {
            System.err.println("Error interacting with Firebase: " + e.getMessage());
        }

        System.out.println("hello");
    }
}
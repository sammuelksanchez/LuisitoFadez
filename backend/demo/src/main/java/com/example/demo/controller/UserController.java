package com.example.demo.controller;


import com.example.demo.model.Appointments;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestParam String verificationCode) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return ResponseEntity.ok(userService.createUser(user,verificationCode));
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<User> getUserById(@PathVariable String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{phoneNumber}")
    public ResponseEntity<User> updateUser(@PathVariable String phoneNumber, @RequestBody User user, @RequestParam String verificationCode) throws ExecutionException, InterruptedException, FirebaseAuthException {
        user.setPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userService.createUser(user, verificationCode));
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String phoneNumber){
        userService.deleteUser(phoneNumber);
        return ResponseEntity.ok().build();
    }

    boolean verify()
    {

        return false;
    }

}

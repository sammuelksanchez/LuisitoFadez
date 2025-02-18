package com.example.demo.controller;


import com.example.demo.model.Appointments;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<User> getUserById(@PathVariable String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{phoneNumber}")
    public ResponseEntity<User> updateUser(@PathVariable String phoneNumber, @RequestBody User user){
        user.setPhoneNumber(phoneNumber);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String phoneNumber){
        userService.deleteUser(phoneNumber);
        return ResponseEntity.ok().build();
    }

}

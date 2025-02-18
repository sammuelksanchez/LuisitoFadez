package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
        private final UserRepository userRepository;
        public UserService(UserRepository userRepository){
            this.userRepository = userRepository;
        }

//      create a new user
        public User createUser(User user){
            if(userRepository.existsByPhoneNumber(user.getPhoneNumber())){
                throw new RuntimeException("User already exists with this phone number: "+ user.getPhoneNumber());
            }
            return userRepository.save(user);
        }

        public Optional<User> getUserByPhoneNumber(String phoneNumber){
            return userRepository.findByPhoneNumber(phoneNumber);
        }

        public User updateUser(String phoneNumber, User updateUserDetails){
            Optional<User> userOpt = userRepository.findByPhoneNumber(phoneNumber);
            if(userOpt.isPresent()){
                User user = userOpt.get();
                user.setFirstName(updateUserDetails.getFirstName());
                user.setLastName(updateUserDetails.getLastName());
                user.setRole("USER");
                return userRepository.save(user);
            }else{
                throw new RuntimeException("User not found with phone number: "+ phoneNumber);
            }
        }

//        Delete user
        public void deleteUser(String phoneNumber){
            if(!userRepository.existsByPhoneNumber(phoneNumber)){
                throw new RuntimeException("User not found: "+ phoneNumber);
            }
            userRepository.deleteByPhoneNumber(phoneNumber);
        }
}

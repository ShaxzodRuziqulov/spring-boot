package com.example.demo.Service;

import com.example.demo.Domain.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public final PasswordEncoder passwordEncoder;
    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public  boolean checkUserName(String userName){
        return userRepository.existsByUserName(userName);
    }
}

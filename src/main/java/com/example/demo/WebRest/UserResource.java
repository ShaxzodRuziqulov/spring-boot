package com.example.demo.WebRest;

import com.example.demo.Service.UserServices;
import com.example.demo.Domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final UserServices userServices;

    public UserResource(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        if (!checkPaswordLength(user.getPassword())) {
            return new ResponseEntity<>("Parol uzunligi 4dan kam", HttpStatus.BAD_REQUEST);
        }
        if (userServices.checkUserName(user.getUserName())){
            return new ResponseEntity<>("Bu user oldin ruyhatdan utgan", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userServices.create(user));
    }

    public boolean checkPaswordLength(String password) {
        return password.length() >= 4;
    }
}

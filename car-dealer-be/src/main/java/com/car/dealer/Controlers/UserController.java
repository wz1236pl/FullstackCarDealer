package com.car.dealer.Controlers;

import java.io.File;
import java.io.IOException;

import com.car.dealer.Klasy.User.User;
import com.car.dealer.Klasy.User.UserRepo;
import com.car.dealer.Requests.EditPasswordRequest;
import com.car.dealer.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PutMapping("/password")
    public <T> ResponseEntity editUserPassword(@RequestBody EditPasswordRequest passwordRequest, HttpServletRequest servletRequest) {
        User user = userService.getUserFromJwt(servletRequest);
        passwordRequest.setEmail(user.getEmail());
        return ResponseEntity.ok(userService.changePassword(passwordRequest));
    }

    @PutMapping("/enabled")
    public void userEnabledChange(boolean enabled, HttpServletRequest servletRequest ) {
        User user = userService.getUserFromJwt(servletRequest);
        user.setEnabled(enabled);
        userRepo.save(user);
    }

}
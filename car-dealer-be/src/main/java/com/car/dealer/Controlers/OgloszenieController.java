package com.car.dealer.Controlers;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.car.dealer.Klasy.Ogloszenie.Ogloszenie;
import com.car.dealer.Klasy.Ogloszenie.OgloszenieRepo;
import com.car.dealer.Klasy.User.User;
import com.car.dealer.Klasy.User.UserRepo;
import com.car.dealer.Security.JwtService;
import com.car.dealer.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class OgloszenieController {
    
    @Autowired
    UserRepo userRepo;
    @Autowired
    OgloszenieRepo ogloszenieRepo;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;

    @PostMapping("/addCar")
    public void addCar(Ogloszenie ogloszenie, HttpServletRequest servletRequest){
        final User user = userService.getUserFromJwt(servletRequest);
        if (user.isEnabled()){
            ogloszenie.setUzytkownik(user);
            ogloszenie.setDataDodania(OffsetDateTime.now());
            ogloszenie.setAktywne(true);
            ogloszenieRepo.save(ogloszenie);
        }
    }

    @GetMapping("/getCar")
    public Ogloszenie getCar(long id){
        return ogloszenieRepo.findById(id).orElse(null);
    }

    @PutMapping("/updateCar")
    public void updateCar(Ogloszenie ogloszenie){
            ogloszenieRepo.save(ogloszenie);
    }

    @DeleteMapping("/deleteCar")
    public void deleteCar(Long id, HttpServletRequest servletRequest) {
        final User user = userService.getUserFromJwt(servletRequest);
        final Ogloszenie ogloszenie = ogloszenieRepo.findById(id).orElse(null);
        if(Objects.nonNull(user) && user.getId().equals(ogloszenie.getUzytkownik().getId())){
            ogloszenieRepo.delete(ogloszenie);
        }
    }


}

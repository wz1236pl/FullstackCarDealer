package com.car.dealer.Controlers;

import java.time.OffsetDateTime;

import com.car.dealer.Klasy.Ogloszenie.Ogloszenie;
import com.car.dealer.Klasy.Ogloszenie.OgloszenieRepo;
import com.car.dealer.Klasy.User.User;
import com.car.dealer.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@AllArgsConstructor
@RestController
public class OgloszenieController {

    @Autowired
    OgloszenieRepo ogloszenieRepo;
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

    @GetMapping("/getCar{id}")
    public Ogloszenie getCar(@RequestParam Long id){
        return ogloszenieRepo.findById(id).orElse(null);
    }

    @PutMapping("/updateCar")
    public void updateCar(Ogloszenie ogloszenie){
        var oldCar = ogloszenieRepo.findById(ogloszenie.getId());
        ogloszenie.setUzytkownik(oldCar.get().getUzytkownik());
        ogloszenieRepo.save(ogloszenie);
    }

    @DeleteMapping("/deleteCar{id}")
    public void deleteCar(@RequestParam Long id) {
        ogloszenieRepo.deleteById(id);
    }


}

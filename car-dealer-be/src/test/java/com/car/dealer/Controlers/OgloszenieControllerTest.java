package com.car.dealer.Controlers;

import com.car.dealer.Klasy.Ogloszenie.Ogloszenie;
import com.car.dealer.Klasy.Ogloszenie.OgloszenieRepo;
import com.car.dealer.Klasy.User.User;
import com.car.dealer.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;


class OgloszenieControllerTest {

    OgloszenieRepo ogloszenieRepo = Mockito.mock(OgloszenieRepo.class);
    UserService userService = Mockito.mock(UserService.class);
    OgloszenieController controller = new OgloszenieController(ogloszenieRepo, userService);

    @Test
    void addCar() {
        //given
        Ogloszenie ogloszenie = Ogloszenie.builder().id(1L).marka("Audi").build();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(userService.getUserFromJwt(Mockito.any())).thenReturn(User.builder().enabled(true).build());
        //when
        controller.addCar(ogloszenie, request);
        //then
        Mockito.verify(ogloszenieRepo, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void getCar() {
        //given
        Ogloszenie ogloszenie = Ogloszenie.builder().id(1L).marka("Audi").build();
        Mockito.when(ogloszenieRepo.findById(1L)).thenReturn(Optional.ofNullable(ogloszenie));
        //when
        Ogloszenie response = controller.getCar(1L);
        //then
        Assertions.assertEquals(response.getId(), 1L);
        Assertions.assertEquals(response.getMarka(), "Audi");
    }

    @Test
    void deleteCar() {
        //when
        controller.deleteCar(1L);
        //then
        Mockito.verify(ogloszenieRepo, Mockito.times(1)).deleteById(Mockito.any());
    }
}
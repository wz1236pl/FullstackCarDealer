package com.car.dealer.Controlers;

import com.car.dealer.Klasy.Ogloszenie.Ogloszenie;
import com.car.dealer.Klasy.Ogloszenie.OgloszenieRepo;
import com.car.dealer.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class OgloszenieControllerIT {
    @MockBean
    private OgloszenieRepo ogloszenieRepo;

    @MockBean
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void getCarByIdTest() {
        //given
        Long carId = 1L;
        Ogloszenie ogloszenie = Ogloszenie.builder().id(1L).marka("Audi").build();
        //when
        when(ogloszenieRepo.findById(carId)).thenReturn(Optional.of(ogloszenie));

        //then
        ResponseEntity<Ogloszenie> response = restTemplate.getForEntity("http://localhost:8080/getCar?id=1", Ogloszenie.class, carId);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), ogloszenie);
    }

}
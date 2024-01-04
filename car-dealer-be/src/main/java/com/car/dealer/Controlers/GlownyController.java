package com.car.dealer.Controlers;

import com.car.dealer.Klasy.Ogloszenie.Ogloszenie;
import com.car.dealer.Klasy.Ogloszenie.OgloszenieRepo;
import com.car.dealer.Klasy.User.Role;
import com.car.dealer.Klasy.User.User;
import com.car.dealer.Klasy.User.UserRepo;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GlownyController {
    @Autowired
    OgloszenieRepo ogloszenieRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public List<Ogloszenie> getAll() {
        return ogloszenieRepo.findAllByOrderByDataDodaniaAsc();
    }

    @GetMapping("/dodajAdmina")
    public void dodajAdmina() {
        userRepo.save(User.builder().email("Admin").nick("Admin").password(passwordEncoder.encode("Admin")).enabled(true).role(Role.ADMIN).build());
    }

    @GetMapping("/dodajAutaDoBazy")
    public void dodajAutaDoBazy(){
        List<Ogloszenie> ogloszenieList = new ArrayList<>();

        Ogloszenie ogloszenie1 = new Ogloszenie(null, OffsetDateTime.now(), "2020", true, "Mercedes-Benz C-Class", "VIN1234567890",
                "KR12345", "Mercedes-Benz", "C-Class", "Czarny", "Sedan", "Benzyna", 5000L, 200, 2000, 45000.0,
                "Nowy samochód, bezwypadkowy", null);
        
        Ogloszenie ogloszenie2 = new Ogloszenie(null, OffsetDateTime.now(), "2020", true, "Mercedes-Benz C-Class", "VIN1234567890",
                "KR12345", "Mercedes-Benz", "C-Class", "Czarny", "Sedan", "Benzyna", 5000L, 200, 2000, 45000.0,
                "Nowy samochód, bezwypadkowy", null);

        Ogloszenie ogloszenie3 = new Ogloszenie(null, OffsetDateTime.now(), "2016", true, "Volkswagen Golf", "VIN6789054321",
                "POZ54321", "Volkswagen", "Golf", "Czerwony", "Hatchback", "Benzyna", 60000L, 150, 1600, 30000.0,
                "Używany samochód, regularnie serwisowany", null);

        Ogloszenie ogloszenie4 = new Ogloszenie(null, OffsetDateTime.now(), "2022", true, "Audi A4", "VIN7890123456",
                "WRO12345", "Audi", "A4", "Srebrny", "Sedan", "Benzyna", 10000L, 250, 2000, 70000.0,
                "Nowy samochód, fabrycznie nowy", null);

        Ogloszenie ogloszenie5 = new Ogloszenie(null, OffsetDateTime.now(), "2017", true, "Toyota Camry", "VIN5432167890",
                "KAT67890", "Toyota", "Camry", "Czarny", "Sedan", "Hybryda", 40000L, 180, 2500, 40000.0,
                "Używany samochód, niski przebieg", null);

        Ogloszenie ogloszenie6 = new Ogloszenie(null, OffsetDateTime.now(), "2019", true, "Ford Mustang", "VIN2345678901",
                "GDA67890", "Ford", "Mustang", "Niebieski", "Coupe", "Benzyna", 2000L, 350, 5000, 90000.0,
                "Używany samochód, mocny silnik", null);

        Ogloszenie ogloszenie7 = new Ogloszenie(null, OffsetDateTime.now(), "2021", true, "Honda Civic", "VIN4567890123",
                "WRO98765", "Honda", "Civic", "Czerwony", "Sedan", "Benzyna", 15000L, 180, 1800, 35000.0,
                "Używany samochód, jednoosobowy właściciel", null);

        Ogloszenie ogloszenie8 = new Ogloszenie(null, OffsetDateTime.now(), "2015", true, "Nissan Qashqai", "VIN5678901234",
                "KR67890", "Nissan", "Qashqai", "Czarny", "SUV", "Diesel", 90000L, 120, 1600, 25000.0,
                "Używany samochód, bardzo oszczędny", null);

        Ogloszenie ogloszenie9 = new Ogloszenie(null, OffsetDateTime.now(), "2018", true, "Mazda CX-5", "VIN6789012345",
                "POZ98765", "Mazda", "CX-5", "Czerwony", "SUV", "Benzyna", 50000L, 180, 2000, 40000.0,
                "Używany samochód, jednoosobowy właściciel", null);

        Ogloszenie ogloszenie10 = new Ogloszenie(null, OffsetDateTime.now(), "2020", true, "Kia Sportage", "VIN7890123456",
                "WAW54321", "Kia", "Sportage", "Biały", "SUV", "Benzyna", 30000L, 150, 1600, 30000.0,
                "Używany samochód, regularnie serwisowany", null);

        Ogloszenie ogloszenie11 = new Ogloszenie(null, OffsetDateTime.now(), "2018", true, "Skoda Octavia", "VIN0987654321",
                "WAW67890", "Skoda", "Octavia", "Czarny", "Sedan", "Diesel", 80000L, 150, 2000, 45000.0,
                "Używany samochód, jednoosobowy właściciel", null);

        Ogloszenie ogloszenie12 = new Ogloszenie(null, OffsetDateTime.now(), "2020", true, "Opel Astra", "VIN1234567890",
                "KR12345", "Opel", "Astra", "Biały", "Hatchback", "Benzyna", 5000L, 120, 1600, 30000.0,
                "Używany samochód, regularnie serwisowany", null);

        Ogloszenie ogloszenie13 = new Ogloszenie(null, OffsetDateTime.now(), "2016", true, "Volvo XC60", "VIN6789054321",
                "POZ54321", "Volvo", "XC60", "Srebrny", "SUV", "Diesel", 60000L, 180, 2400, 65000.0,
                "Używany samochód, jednoosobowy właściciel", null);

        Ogloszenie ogloszenie14 = new Ogloszenie(null, OffsetDateTime.now(), "2017", true, "Skoda Superb", "VIN7890123456",
                "WRO12345", "Skoda", "Superb", "Szary", "Sedan", "Benzyna", 40000L, 200, 2000, 40000.0,
                "Używany samochód, niski przebieg", null);

        Ogloszenie ogloszenie15 = new Ogloszenie(null, OffsetDateTime.now(), "2019", true, "Opel Corsa", "VIN5432167890",
                "KAT67890", "Opel", "Corsa", "Czerwony", "Hatchback", "Benzyna", 2000L, 100, 1400, 20000.0,
                "Używany samochód, ekonomiczny", null);

        Ogloszenie ogloszenie16 = new Ogloszenie(null, OffsetDateTime.now(), "2021", true, "Volvo S60", "VIN2345678901",
                "GDA67890", "Volvo", "S60", "Niebieski", "Sedan", "Benzyna", 10000L, 250, 2000, 70000.0,
                "Nowy samochód, fabrycznie nowy", null);

        Ogloszenie ogloszenie17 = new Ogloszenie(null, OffsetDateTime.now(), "2015", true, "Skoda Yeti", "VIN4567890123",
                "WRO98765", "Skoda", "Yeti", "Czarny", "SUV", "Diesel", 90000L, 120, 1600, 25000.0,
                "Używany samochód, bardzo oszczędny", null);

        Ogloszenie ogloszenie18 = new Ogloszenie(null, OffsetDateTime.now(), "2018", true, "Opel Insignia", "VIN5678901234",
                "KR67890", "Opel", "Insignia", "Czerwony", "Sedan", "Diesel", 50000L, 180, 2000, 40000.0,
                "Używany samochód, jednoosobowy właściciel", null);

        Ogloszenie ogloszenie19 = new Ogloszenie(null, OffsetDateTime.now(), "2020", true, "Volvo V40", "VIN6789012345",
                "POZ98765", "Volvo", "V40", "Biały", "Hatchback", "Benzyna", 30000L, 150, 1600, 30000.0,
                "Używany samochód, regularnie serwisowany", null);

        Ogloszenie ogloszenie20 = new Ogloszenie(null, OffsetDateTime.now(), "2019", true, "Skoda Kodiaq", "VIN7890123456",
                "WAW54321", "Skoda", "Kodiaq", "Srebrny", "SUV", "Benzyna", 40000L, 160, 2000, 35000.0,
                "Używany samochód, jednoosobowy właściciel", null);

        Ogloszenie ogloszenie21 = new Ogloszenie(null, OffsetDateTime.now(), "2016", true, "Opel Mokka", "VIN0987654321",
                "GDA67891", "Opel", "Mokka", "Czerwony", "SUV", "Benzyna", 80000L, 140, 1600, 25000.0,
                "Używany samochód, regularnie serwisowany", null);

        ogloszenieList.add(ogloszenie1);        
        ogloszenieList.add(ogloszenie2);
        ogloszenieList.add(ogloszenie3);
        ogloszenieList.add(ogloszenie4);
        ogloszenieList.add(ogloszenie5);
        ogloszenieList.add(ogloszenie6);
        ogloszenieList.add(ogloszenie7);
        ogloszenieList.add(ogloszenie8);
        ogloszenieList.add(ogloszenie9);
        ogloszenieList.add(ogloszenie10);
        ogloszenieList.add(ogloszenie11);
        ogloszenieList.add(ogloszenie12);
        ogloszenieList.add(ogloszenie13);
        ogloszenieList.add(ogloszenie14);
        ogloszenieList.add(ogloszenie15);
        ogloszenieList.add(ogloszenie16);
        ogloszenieList.add(ogloszenie17);
        ogloszenieList.add(ogloszenie18);
        ogloszenieList.add(ogloszenie19);
        ogloszenieList.add(ogloszenie20);
        ogloszenieList.add(ogloszenie21);

        ogloszenieRepo.saveAll(ogloszenieList);
    }

}

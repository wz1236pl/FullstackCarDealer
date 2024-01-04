package com.car.dealer.Klasy.Ogloszenie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.car.dealer.Klasy.User.User;

import java.time.OffsetDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ogloszenie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private OffsetDateTime dataDodania;
    private String rok;
    private boolean aktywne;
    private String nazwa;
    private String vin;
    private String nrRej;
    private String marka;
    private String model;
    private String kolor;
    private String typ;
    private String paliwo;
    private Long przebieg;
    private Integer moc;
    private Integer pojemnosc;
    private double cena;
    private String opis;
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("ogloszenia") 
    private User uzytkownik;

    @Override
public String toString() {
    return "Ogloszenie{" +
            "id=" + id +
            ", data_dodania=" + dataDodania +
            ", aktywne=" + aktywne +
            ", nazwa='" + nazwa + '\'' +
            ", vin='" + vin + '\'' +
            ", nrRej='" + nrRej + '\'' +
            ", marka='" + marka + '\'' +
            ", model='" + model + '\'' +
            ", kolor='" + kolor + '\'' +
            ", typ='" + typ + '\'' +
            ", paliwo='" + paliwo + '\'' +
            ", przebieg=" + przebieg +
            ", moc=" + moc +
            ", pojemnosc=" + pojemnosc +
            ", cena=" + cena +
            '}';
}

}



package com.example.magacin.dto;

import com.example.magacin.kategorije.Kategorija;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProzivodUpdateDto {
    @NonNull
    private Integer idProizvoda;

    private String nazivProizvoda;

    private Double cenaProizvoda;

    private Integer dostupnaKolicina;

    private String opis;

    private Integer idKategorije;

    private String nazivKategorije;

    private Kategorija kategorija;

}

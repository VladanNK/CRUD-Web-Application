package com.example.magacin.proizvodi;

import com.example.magacin.kategorije.Kategorija;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;


@Entity
@Table()
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proizvod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProizvoda;
    @Column
    private String nazivProizvoda;
    @Column
    private Double cenaProizvoda;
    @Column
    private Integer dostupnaKolicina;
    @Column
    private String opis;
    @Column
    private Integer idKategorije;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "KATEGORIJA_ID_KATEGORIJE", nullable = true)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Kategorija kategorija;
}
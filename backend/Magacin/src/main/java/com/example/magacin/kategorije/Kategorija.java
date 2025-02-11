package com.example.magacin.kategorije;

import com.example.magacin.proizvodi.Proizvod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Kategorija")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idKategorije;
    @Column
    private String nazivKategorije;
}

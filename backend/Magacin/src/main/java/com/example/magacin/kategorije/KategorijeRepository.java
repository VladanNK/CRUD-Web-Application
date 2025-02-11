package com.example.magacin.kategorije;

import com.example.magacin.proizvodi.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KategorijeRepository extends JpaRepository<Kategorija, Integer> {

    Kategorija getKategorijaByIdKategorije(Integer id);
    Kategorija getKategorijaByNazivKategorije(String nazivKategorije);

}

package com.example.magacin.service;

import com.example.magacin.dto.ProzivodUpdateDto;
import com.example.magacin.proizvodi.Proizvod;
import com.example.magacin.proizvodi.ProizvodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProizvodService {
    //final jer ne moze doci do promene pa i ne mora @Autowired
    private final ProizvodRepository proizvodRepository;

//    public ProizvodService (ProizvodRepository proizvodRepository){
//        this.proizvodRepository = proizvodRepository;
//    }

    public List<Proizvod> uzmiSveProizvode()
    {
        return proizvodRepository.findAll();

    }

    public void kreirajProizvod(Proizvod proizvod)
    {
        proizvodRepository.save(proizvod);
    }
    public List<Proizvod> uzmiProizvodePoIdKategorije(Integer id){
        return proizvodRepository.getAllByIdKategorije(id);
    }

    public void obrisiProizvod(Integer id){
        proizvodRepository.deleteById(id);
    }

    public void azurirajProizvod(Proizvod proizvod){

        proizvodRepository.save(proizvod);
    }

    public Proizvod uzmiProizvodPoId(Integer id){
        return proizvodRepository.getProizvodByIdProizvoda(id);
    }

    public List<Proizvod> uzmiProizvodPoImenu(String naziv){
        return proizvodRepository.findByName(naziv);
    }

    public List<Proizvod> uzmiSveProizvodePoKolicini(Integer kolicina){
        return proizvodRepository.getProizvodByDostupnaKolicina(kolicina);
    }

    public List<Proizvod> uzmiSveProizvodePoCeni(Double cena){
        return proizvodRepository.getProizvodByCenaProizvoda(cena);
    }


    /*@Transactional
    public void updateProizvod(Integer id, ProzivodUpdateDto proizvod) {
        Proizvod p = proizvodRepository.findById(id).orElseThrow(()->
                new RuntimeException("nema trazenog id-ja proizvoda"));
        p.setNazivKategorije(proizvod.getNazivKategorije());
        proizvodRepository.save(p);
    }*/
}

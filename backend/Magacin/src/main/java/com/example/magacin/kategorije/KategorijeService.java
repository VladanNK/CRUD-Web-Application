package com.example.magacin.kategorije;

import com.example.magacin.proizvodi.Proizvod;
import com.example.magacin.proizvodi.ProizvodRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KategorijeService {
    @Autowired
    private KategorijeRepository kategorijaRepository;

    @Autowired
    private ProizvodRepository proizvodRepository;

    public List<Kategorija> vratiSveKategorije(){
        return kategorijaRepository.findAll();
    }
    public void kreirajKategoriju(Kategorija kategorija){
        kategorijaRepository.save(kategorija);
    }
    public Kategorija vratiKategorijuPoId(Integer id){
        return kategorijaRepository.getKategorijaByIdKategorije(id);
    }
    public void obrisiKategoriju(Integer id){
        kategorijaRepository.deleteById(id);
    }
    public void azurirajKategoriju(Kategorija kategorija){
        kategorijaRepository.save(kategorija);
    }
    public Kategorija vratiKategorijuPoImenu(String naziv){ return kategorijaRepository.getKategorijaByNazivKategorije(naziv); }



    public void deleteRoditeljskiEntitet(Integer id) {
        Kategorija kategorija = kategorijaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nije pronadjena kategorija"));

        List<Proizvod> povezaniProizvodi = proizvodRepository.findByKategorija(kategorija);

        for (Proizvod proizvod : povezaniProizvodi) {
            proizvod.setKategorija(null);
            proizvodRepository.save(proizvod);
        }
        kategorijaRepository.delete(kategorija);
    }
}

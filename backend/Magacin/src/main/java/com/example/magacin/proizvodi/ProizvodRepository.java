package com.example.magacin.proizvodi;

import com.example.magacin.kategorije.Kategorija;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Integer> {

    Proizvod getProizvodByIdProizvoda(Integer idProizvoda);
    List<Proizvod> getAllByIdKategorije(Integer id);
    //Proizvod getProizvodByNazivProizvoda(String nazivProizvoda);
    List<Proizvod> getProizvodByDostupnaKolicina(Integer kolicinaProizvoda);
    List<Proizvod> getProizvodByCenaProizvoda(Double cenaProizvoda);

    @Query(value = "SELECT * FROM Proizvod where lower(NAZIV_PROIZVODA) like lower(concat('%', :name,'%'))", nativeQuery = true)
    public List<Proizvod> findByName(@Param("name") String name);

    List<Proizvod> findByKategorija(Kategorija kategorija);

    /*@Modifying
    @Query("DELETE FROM Kategorija k WHERE k.idKategorije = :idKategorije")
    public void deleteKategorije(@Param("idKategorije") Integer idKategorije);*/

    //@Query(value = "SELECT * FROM Proizvod where lower(NAZIV_KATEGORIJE) like lower(concat('%', :name,'%'))", nativeQuery = true)
  //public List<Proizvod> findAllByKategorija(Integer id);
  //public List<Proizvod> findAllByNaziv(String name);
 }

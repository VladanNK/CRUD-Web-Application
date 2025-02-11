package com.example.magacin.proizvodi;

import com.example.magacin.dto.ProzivodUpdateDto;
import com.example.magacin.service.ProizvodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
public class ProizvodController {

    @Autowired
    private ProizvodService proizvodService;

    @GetMapping("/proizvodi")
    public List<Proizvod> getAllProizvodi(){
        return proizvodService.uzmiSveProizvode();
    }

    @PostMapping("/newProizvod")
    public String newProzivod(@RequestBody Proizvod proizvod){
        proizvodService.kreirajProizvod(proizvod);
        return "Uspesno kreiran nov proizvod";
    }

    @DeleteMapping("/proizvodDelete/{id}")
    public String deleteProizvod(@PathVariable Integer id){
        proizvodService.obrisiProizvod(id);
        return "Uspesno obrisan proizvod";
    }

    /*@PutMapping("/proizvodUpdate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProizvod(@PathVariable Integer id, @RequestBody @Validated ProzivodUpdateDto proizvod){
        proizvodService.updateProizvod(id, proizvod);
//        if(id.equals(proizvod.getIdProizvoda())){
//            proizvodService.azurirajProizvod(proizvod);
//            return "Uspesno azuriran proizvod";
//        } else {
//            proizvodService.kreirajProizvod(proizvod);
//            return "Uspesno kreiran proizvod";
//        }

        return "sve ok";
    }*/

    @PutMapping("proizvodUpdate/{id}")
    public String updateProizvod(@PathVariable Integer id, @RequestBody Proizvod proizvod){
        proizvodService.azurirajProizvod(proizvod);
        return "Azuriran proizvod";
    }

    @GetMapping("/proizvodiKategorije/{id}")
    public List<Proizvod> getProizvodByIdKategorije(@PathVariable Integer id){
        return proizvodService.uzmiProizvodePoIdKategorije(id);
    }

    @GetMapping("/proizvodPoId/{id}")
    public Proizvod getProizvodById(@PathVariable Integer id){
        return proizvodService.uzmiProizvodPoId(id);
    }

    @GetMapping("/proizvodByName/{name}")
    public List<Proizvod> getProizvodByName(@PathVariable String name){
        return proizvodService.uzmiProizvodPoImenu(name);
    }

    @GetMapping("/proizvodiByKolicina/{kolicina}")
    public List<Proizvod> getProizvodiByKolicina(@PathVariable Integer kolicina){
        return proizvodService.uzmiSveProizvodePoKolicini(kolicina);
    }

    @GetMapping("/proizvodiByCena/{cena}")
    public List<Proizvod> getProizvodiByCena(@PathVariable Double cena){
        return proizvodService.uzmiSveProizvodePoCeni(cena);
    }

    

}

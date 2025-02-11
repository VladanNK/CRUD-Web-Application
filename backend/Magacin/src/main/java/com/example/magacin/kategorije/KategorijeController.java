package com.example.magacin.kategorije;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST})
public class KategorijeController {
    @Autowired
    private KategorijeService kategorijaService;

    @GetMapping("/kategorije")
    public List<Kategorija> getAllKategorije(){
        return kategorijaService.vratiSveKategorije();
    }

    @PostMapping("/newKategorija")
    public String createKategorija(@RequestBody Kategorija kategorija){
        System.out.println(kategorija);
        kategorijaService.kreirajKategoriju(kategorija);
        return "Uspesno kreirana kategorija";
    }
    @GetMapping("/kategorija/{id}")
    public Kategorija getKategorijaById(@PathVariable Integer id){
        return kategorijaService.vratiKategorijuPoId(id);
    }

    @DeleteMapping("/kategorijaDelete/{id}")
    public String deleteKategorija(@PathVariable Integer id){
        kategorijaService.obrisiKategoriju(id);
        return "Uspesno obrisana kategorija";
    }
    @PutMapping("/kategorijaUpdate/{id}")
    public String updateKategorija(@PathVariable Integer id, @RequestBody Kategorija kategorija){
        if(id.equals(kategorija.getIdKategorije())){
            kategorijaService.azurirajKategoriju(kategorija);
            return "Uspesno azurirana kategorija";
        } else {
            kategorijaService.kreirajKategoriju(kategorija);
            return "Uspesno kreirana kategorija";
        }
    }
    @GetMapping("/getKategorijaByName/{name}")
    public Kategorija getKategorijaByName(@PathVariable String name){
        return kategorijaService.vratiKategorijuPoImenu(name);
    }


    @DeleteMapping("/deleteKategorija2/{id}")
    public void deleteKategorija2(@PathVariable Integer id){
        kategorijaService.deleteRoditeljskiEntitet(id);
    }
}

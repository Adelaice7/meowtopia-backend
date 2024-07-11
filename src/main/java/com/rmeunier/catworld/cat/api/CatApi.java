package com.rmeunier.catworld.cat.api;

import com.rmeunier.catworld.cat.service.CatService;
import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cats")
public class CatApi {

    private final CatService catService;

    @Autowired
    public CatApi(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats() {
        return new ResponseEntity<>(catService.getAllCats(), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<Cat> getCatById(@PathVariable("catId") UUID id) {
        return new ResponseEntity<>(catService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/breed/{breedId}")
    public ResponseEntity<List<Cat>> getCatsByBreed(@PathVariable("breedId") UUID breedId) {
        return new ResponseEntity<>(catService.findByBreedId(breedId), HttpStatus.OK);
    }

    @GetMapping("/breed/{breedName}")
    public ResponseEntity<List<Cat>> getCatsByBreedName(@PathVariable("breedName") String breedName) {
        return new ResponseEntity<>(catService.findByBreedName(breedName), HttpStatus.OK);
    }

    @PutMapping("/breed/{breedId}")
    public ResponseEntity<Cat> createCat(@PathVariable("breedId") UUID breedId, @RequestBody Cat cat) {
        return new ResponseEntity<>(catService.createCat(breedId, cat), HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<Cat> updateCat(@PathVariable("catId") UUID id, @RequestBody Cat updatedCat) {
        return new ResponseEntity<>(catService.modifyCat(id, updatedCat), HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> deleteCat(@PathVariable("catId") UUID id) {
        catService.deleteCat(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

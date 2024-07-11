package com.rmeunier.catworld.cat.api;

import com.rmeunier.catworld.cat.service.BreedService;
import com.rmeunier.catworld.cat.model.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/breeds")
public class BreedApi {
    private final BreedService breedService;

    @Autowired
    public BreedApi(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping
    public ResponseEntity<List<Breed>> getAllBreeds() {
        return new ResponseEntity<>(breedService.getAllBreeds(), HttpStatus.OK);
    }

    @GetMapping("/{breedId}")
    public ResponseEntity<Breed> getBreedById(@PathVariable("breedId") UUID breedId) {
        return new ResponseEntity<>(breedService.getBreedById(breedId), HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Breed> createBreed(@RequestBody Breed breed) {
        return new ResponseEntity<>(breedService.createBreed(breed), HttpStatus.CREATED);
    }

    @PutMapping("/{breedId}")
    public ResponseEntity<Breed> updateBreed(@PathVariable("breedId") UUID breedId, @RequestBody Breed updatedBreed) {
        return new ResponseEntity<>(breedService.updateBreed(breedId, updatedBreed), HttpStatus.OK);
    }

    @DeleteMapping("/{breedId}")
    public ResponseEntity<Void> deleteBreed(@PathVariable("breedId") UUID breedId) {
        breedService.deleteBreed(breedId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

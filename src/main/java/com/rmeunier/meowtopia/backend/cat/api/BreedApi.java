package com.rmeunier.meowtopia.backend.cat.api;

import com.rmeunier.meowtopia.backend.cat.model.dto.BreedDto;
import com.rmeunier.meowtopia.backend.cat.service.IBreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/breeds")
public class BreedApi {
    private final IBreedService breedService;

    @Autowired
    public BreedApi(IBreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping
    public ResponseEntity<Page<BreedDto>> getAllBreeds(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "name") String orderBy,
                                                       @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(breedService.getAllBreeds(page, size, orderBy, direction));
    }

    @GetMapping("/{breedId}")
    public ResponseEntity<BreedDto> getBreedById(@PathVariable("breedId") UUID breedId) {
        return ResponseEntity.ok(breedService.getBreedById(breedId));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BreedDto> createBreed(@RequestBody BreedDto breed) {
        return ResponseEntity.status(HttpStatus.CREATED).body(breedService.createBreed(breed));
    }

    @PutMapping("/{breedId}")
    public ResponseEntity<BreedDto> updateBreed(@PathVariable("breedId") UUID breedId, @RequestBody BreedDto updateBreed) {
        BreedDto updatedBreed = breedService.updateBreed(breedId, updateBreed);
        if (updatedBreed != null) {
            return ResponseEntity.ok(updatedBreed);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{breedId}")
    public ResponseEntity<Void> deleteBreed(@PathVariable("breedId") UUID breedId) {
        if (breedService.deleteBreed(breedId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.rmeunier.catworld.cat.api;

import com.rmeunier.catworld.cat.model.dto.CatDto;
import com.rmeunier.catworld.cat.service.CatService;
import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<CatDto>> getAllCats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/user/{userAccountId}")
    public ResponseEntity<List<CatDto>> getAllCatsByUserAccountId(@PathVariable("userAccountId") UUID userAccountId) {
        return ResponseEntity.ok(catService.getAllCatsByUserAccountId(userAccountId));
    }

    @GetMapping("/user/{userAccountId}/filtered")
    public ResponseEntity<Page<CatDto>> getAllCatsByUserAccountIdFiltered(@PathVariable("userAccountId") UUID userAccountId,
                                                                          @RequestParam("page") Integer page,
                                                                          @RequestParam("size") Integer size,
                                                                          @RequestParam("orderBy") String orderBy,
                                                                          @RequestParam("direction") String direction) {
        return ResponseEntity.ok(catService.getAllCatsByUserAccountIdFiltered(userAccountId, page, size, orderBy, direction));
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CatDto> getCatById(@PathVariable("catId") UUID catId) {
        return ResponseEntity.ok(catService.findById(catId));
    }

    @GetMapping("/breed/{breedId}")
    public ResponseEntity<List<CatDto>> getCatsByBreed(@PathVariable("breedId") UUID breedId) {
        return ResponseEntity.ok(catService.findByBreedId(breedId));
    }

    @GetMapping("/breed/{breedName}")
    public ResponseEntity<List<CatDto>> getCatsByBreedName(@PathVariable("breedName") String breedName) {
        return ResponseEntity.ok(catService.findByBreedName(breedName));
    }

    @PutMapping("/breed/{breedId}")
    public ResponseEntity<CatDto> createCat(@PathVariable("breedId") UUID breedId, @RequestBody CatDto cat) {
        return new ResponseEntity<>(catService.createCat(breedId, cat), HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CatDto> updateCat(@PathVariable("catId") UUID id, @RequestBody CatDto updatedCat) {
        return ResponseEntity.ok(catService.modifyCat(id, updatedCat));
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> deleteCat(@PathVariable("catId") UUID id) {
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }
}

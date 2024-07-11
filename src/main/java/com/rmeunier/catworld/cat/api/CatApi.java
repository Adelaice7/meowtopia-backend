package com.rmeunier.catworld.cat.api;

import com.rmeunier.catworld.cat.model.dto.CatDto;
import com.rmeunier.catworld.cat.service.ICatService;
import jakarta.validation.Valid;
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

    private final ICatService catService;

    @Autowired
    public CatApi(ICatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public ResponseEntity<Page<CatDto>> getAllCats(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "createdAt") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        return ResponseEntity.ok(catService.getAllCats(page, size, orderBy, direction));
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
        CatDto catDto = catService.findById(catId);
        if (catDto != null) {
            return ResponseEntity.ok(catDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CatDto> createCat(@Valid @RequestBody CatDto catDto) {
        CatDto createdCat = catService.createCat(catDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCat);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CatDto> updateCat(@PathVariable("catId") UUID catId, @Valid @RequestBody CatDto updateCat) {
        CatDto updatedCat = catService.updateCat(catId, updateCat);
        if (updatedCat != null) {
            return ResponseEntity.ok(updatedCat);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<Void> deleteCat(@PathVariable("catId") UUID catId) {
        if (catService.deleteCat(catId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

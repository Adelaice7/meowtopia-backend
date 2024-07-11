package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.exception.BreedNotFoundException;
import com.rmeunier.catworld.cat.mapper.BreedMapper;
import com.rmeunier.catworld.cat.mapper.CatMapper;
import com.rmeunier.catworld.cat.model.Breed;
import com.rmeunier.catworld.cat.model.dto.BreedDto;
import com.rmeunier.catworld.cat.model.dto.CatDto;
import com.rmeunier.catworld.cat.repository.CatRepository;
import com.rmeunier.catworld.cat.exception.CatNotFoundException;
import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CatServiceImpl implements ICatService {
    private final CatRepository catRepository;

    private final IBreedService breedService;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, IBreedService breedService) {
        this.catRepository = catRepository;
        this.breedService = breedService;
    }

    @Override
    public CatDto findById(UUID catId) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new CatNotFoundException(catId));
        if (cat != null) {
            return CatMapper.INSTANCE.mapToDto(cat);
        }
        return null;
    }

    @Override
    public List<CatDto> getAllCats() {
        return catRepository.findAll()
                .stream().map(CatMapper.INSTANCE::mapToDto)
                .toList();
    }

    @Override
    public Page<CatDto> getAllCats(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return catRepository.findAll(pageRequest)
                .map(CatMapper.INSTANCE::mapToDto);
    }

    @Override
    public List<CatDto> getAllCatsByUserAccountId(UUID userAccountId) {
        return catRepository.findByUserAccountUserAccountId(userAccountId)
                .stream().map(CatMapper.INSTANCE::mapToDto)
                .toList();
    }

    @Override
    public Page<CatDto> getAllCatsFiltered(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return catRepository.findAll(pageRequest)
                .map(CatMapper.INSTANCE::mapToDto);
    }

    @Override
    public Page<CatDto> getAllCatsByUserAccountIdFiltered(UUID userAccountId, Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return catRepository.findByUserAccountUserAccountId(userAccountId, pageRequest)
                .map(CatMapper.INSTANCE::mapToDto);
    }

    @Override
    public CatDto createCat(UUID breedId, CatDto catDto) {
        Cat cat = CatMapper.INSTANCE.mapToEntity(catDto);

        // TODO validation
        BreedDto breedDtoById = breedService.getBreedById(breedId);
        Breed breedById = BreedMapper.mapToEntity(breedDtoById);

        if (breedById == null) {
            throw new BreedNotFoundException(breedId);
        }
        cat.setBreed(breedById);

        LocalDate currentDate = LocalDate.now();
        if (cat.getBirthDate() == null) {
            cat.setBirthDate(currentDate);
        } else {
            cat.setAge(cat.calculateAge());
        }
        Cat save = catRepository.save(cat);
        return CatMapper.INSTANCE.mapToDto(save);
    }

    @Override
    public CatDto createCat(CatDto catDto) {
        Cat cat = CatMapper.INSTANCE.mapToEntity(catDto);
        cat = catRepository.save(cat);
        return CatMapper.INSTANCE.mapToDto(cat);
    }

    @Override
    public CatDto updateCat(UUID catId, CatDto updatedCatDto) {
        Cat updatedCat = CatMapper.INSTANCE.mapToEntity(updatedCatDto);
        Cat existingCat = catRepository.findById(catId)
                .orElseThrow(() -> new CatNotFoundException(catId));

        if (existingCat == null) {
            throw new CatNotFoundException(catId);
        }

        existingCat.setName(updatedCat.getName());
        existingCat.setBreed(updatedCat.getBreed());
        existingCat.setColor(updatedCat.getColor());

        if (updatedCat.getBirthDate() != null && existingCat.getBirthDate() != updatedCat.getBirthDate()) {
            existingCat.setBirthDate(updatedCat.getBirthDate());
            existingCat.setAge(updatedCat.calculateAge());
        }
        existingCat.setGender(updatedCat.getGender());
        existingCat.setWeight(updatedCat.getWeight());
        existingCat.setFixed(updatedCat.isFixed());

        existingCat.setIntelligence(updatedCat.getIntelligence());
        existingCat.setSociability(updatedCat.getSociability());
        existingCat.setActivity(updatedCat.getActivity());
        existingCat.setCuriosity(updatedCat.getCuriosity());
        existingCat.setIndependence(updatedCat.getIndependence());
        existingCat.setStubbornness(updatedCat.getStubbornness());
        existingCat.setPlayfulness(updatedCat.getPlayfulness());
        existingCat.setAffection(updatedCat.getAffection());

        existingCat.setHappiness(updatedCat.getHappiness());
        existingCat.setHunger(updatedCat.getHunger());
        existingCat.setThirst(updatedCat.getThirst());
        existingCat.setHealth(updatedCat.getHealth());
        existingCat.setEnergy(updatedCat.getEnergy());
        existingCat.setCleanliness(updatedCat.getCleanliness());

        Cat save = catRepository.save(existingCat);
        return CatMapper.INSTANCE.mapToDto(save);
    }

    @Override
    public boolean deleteCat(UUID catId) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new CatNotFoundException(catId));
        if (cat != null) {
            catRepository.delete(cat);
            return true;
        }
        return false;
    }

    @Override
    public List<CatDto> findByBreedId(UUID breedId) {
        List<CatDto> cats = catRepository.findByBreedBreedId(breedId)
                .stream().map(CatMapper.INSTANCE::mapToDto)
                .toList();
        if (cats.isEmpty()) {
            throw new CatNotFoundException();
        }
        return cats;
    }

    public List<CatDto> findByBreedName(String breedName) {
        List<CatDto> cats = catRepository.findByBreedName(breedName)
                .stream().map(CatMapper.INSTANCE::mapToDto)
                .toList();
        if (cats.isEmpty()) {
            throw new CatNotFoundException();
        }
        return cats;
    }

    @Override
    public void updateAllCatAges() {
        List<Cat> allCats = catRepository.findAll();
        for (Cat cat : allCats) {
            cat.setAge(cat.getAge() + 1);
            catRepository.save(cat);
        }
    }
}

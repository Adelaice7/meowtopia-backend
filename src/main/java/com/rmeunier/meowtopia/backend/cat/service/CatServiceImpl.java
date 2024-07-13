package com.rmeunier.meowtopia.backend.cat.service;

import com.rmeunier.meowtopia.backend.cat.exception.BreedNotFoundException;
import com.rmeunier.meowtopia.backend.cat.mapper.BreedMapper;
import com.rmeunier.meowtopia.backend.cat.mapper.CatMapper;
import com.rmeunier.meowtopia.backend.cat.model.Breed;
import com.rmeunier.meowtopia.backend.cat.model.dto.BreedDto;
import com.rmeunier.meowtopia.backend.cat.model.dto.CatDto;
import com.rmeunier.meowtopia.backend.cat.repository.CatRepository;
import com.rmeunier.meowtopia.backend.cat.exception.CatNotFoundException;
import com.rmeunier.meowtopia.backend.cat.model.Cat;
import com.rmeunier.meowtopia.backend.cat.utils.DateConverterUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
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

        BreedDto breedDtoById = breedService.getBreedById(breedId);
        Breed breedById = BreedMapper.mapToEntity(breedDtoById);

        if (breedById == null) {
            throw new BreedNotFoundException(breedId);
        }
        cat.setBreed(breedById);

        LocalDate currentDate = LocalDate.now();
        if (cat.getCreatedAt() == null) {
            cat.setCreatedAt(DateConverterUtil.localDateToDate(currentDate));
        } else {
            cat.setAgeInDays(cat.calculateAge());
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
        existingCat.setFurColor(updatedCat.getFurColor());
        existingCat.setEyeColor(updatedCat.getEyeColor());

        LocalDate currentDate = LocalDate.now();
        if (updatedCat.getUpdatedAt() != null && existingCat.getUpdatedAt() != updatedCat.getUpdatedAt()) {
            existingCat.setUpdatedAt(DateConverterUtil.localDateToDate(currentDate));
            existingCat.setAgeInDays(updatedCat.calculateAge());
        }

        existingCat.setGender(updatedCat.getGender());
        existingCat.setWeight(updatedCat.getWeight());
        existingCat.setFixed(updatedCat.isFixed());

        existingCat.setIntelligence(updatedCat.getIntelligence());
        existingCat.setCuriosity(updatedCat.getCuriosity());
        existingCat.setIndependence(updatedCat.getIndependence());
        existingCat.setPlayfulness(updatedCat.getPlayfulness());
        existingCat.setAffectionate(updatedCat.getAffectionate());

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

    @Override
    public Page<CatDto> findByBreedId(UUID breedId, Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return catRepository.findByBreedBreedId(breedId, pageRequest)
                .map(CatMapper.INSTANCE::mapToDto);
    }

    @Override
    @Transactional
    @Async
    public void updateAllCatAgesAndLifeStages() {
        List<Cat> cats = catRepository.findAll();
        cats.parallelStream().forEach(cat -> {
            cat.updateCatAgeAndLifeStage();
            catRepository.save(cat);
        });
    }
}

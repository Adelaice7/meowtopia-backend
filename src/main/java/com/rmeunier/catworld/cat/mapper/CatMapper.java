package com.rmeunier.catworld.cat.mapper;

import com.rmeunier.catworld.cat.model.Cat;
import com.rmeunier.catworld.cat.model.dto.CatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CatMapper {

    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    @Mapping(target = "catId", source = "catId")
    Cat mapToEntity(CatDto catDto);

    CatDto mapToDto(Cat cat);
}

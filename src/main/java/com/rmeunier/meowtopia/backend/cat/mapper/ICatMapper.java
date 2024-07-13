package com.rmeunier.meowtopia.backend.cat.mapper;

import com.rmeunier.meowtopia.backend.cat.model.Cat;
import com.rmeunier.meowtopia.backend.cat.model.dto.CatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ICatMapper {

    ICatMapper INSTANCE = Mappers.getMapper(ICatMapper.class);

    @Mapping(target = "catId", source = "catId")
    Cat mapToEntity(CatDto catDto);

    CatDto mapToDto(Cat cat);
}

package com.codecool.talee.mapper;

import com.codecool.talee.DTO.location.NewOpeningHoursDTO;
import com.codecool.talee.DTO.location.NewOpeningHoursWithoutLocationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpeningHoursMapper {
    OpeningHoursMapper INSTANCE = Mappers.getMapper(OpeningHoursMapper.class);
    NewOpeningHoursDTO openingHoursToNewOpeningHoursDTO(NewOpeningHoursWithoutLocationDTO openingHours);
}

package com.codecool.talee.mapper;

import com.codecool.talee.DTO.location.NewOpeningHoursDTO;
import com.codecool.talee.DTO.location.NewOpeningHoursWithoutLocationDTO;
import com.codecool.talee.DTO.location.OpeningHoursDTO;
import com.codecool.talee.model.locations.OpeningHours;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpeningHoursMapper {
    OpeningHoursMapper INSTANCE = Mappers.getMapper(OpeningHoursMapper.class);
    OpeningHoursDTO openingHourstoOpeningHoursDTO(OpeningHours openingHours);
    NewOpeningHoursDTO openingHourstoNewOpeningHoursDTO(NewOpeningHoursWithoutLocationDTO openingHours);
}

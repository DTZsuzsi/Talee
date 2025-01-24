package com.codecool.mapper;

import com.codecool.DTO.location.NewOpeningHoursDTO;
import com.codecool.DTO.location.NewOpeningHoursWithoutLocationDTO;
import com.codecool.DTO.location.OpeningHoursDTO;
import com.codecool.model.locations.OpeningHours;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpeningHoursMapper {
    OpeningHoursMapper INSTANCE = Mappers.getMapper(OpeningHoursMapper.class);
    OpeningHoursDTO openingHourstoOpeningHoursDTO(OpeningHours openingHours);
    NewOpeningHoursDTO openingHourstoNewOpeningHoursDTO(NewOpeningHoursWithoutLocationDTO openingHours);
}

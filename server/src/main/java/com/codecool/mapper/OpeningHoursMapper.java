package com.codecool.mapper;

import com.codecool.DTO.location.LocationWithoutOpeningHoursDTO;
import com.codecool.DTO.location.NewOpeningHoursDTO;
import com.codecool.DTO.location.NewOpeningHoursWithoutLocationDTO;
import com.codecool.DTO.location.OpeningHoursDTO;
import com.codecool.model.locations.Location;
import com.codecool.model.locations.OpeningHours;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OpeningHoursMapper {
  OpeningHoursMapper INSTANCE = Mappers.getMapper(OpeningHoursMapper.class);

  @Mapping(target = "day", source = "dayOfWeek")
  OpeningHoursDTO openingHoursToOpeningHoursDTO(OpeningHours openingHours);
//  OpeningHours openingHoursDTOToOpeningHours(OpeningHoursDTO openingHoursDTO);
  @Mapping(target = "location", source = "existingLocation")
//  @Mapping(target = "dayOfWeek", source = "day")
  OpeningHours newOpeningHoursDTOToOpeningHours(NewOpeningHoursDTO newOpeningHoursDTO, Location existingLocation);
//  @Mapping(target = "day", source = "dayOfWeek")
  NewOpeningHoursDTO newOpeningHoursWithoutLocationToNewOpeningHoursDTO(NewOpeningHoursWithoutLocationDTO newOpeningHoursWithoutLocationDTO,
                                                                        LocationWithoutOpeningHoursDTO locationWithoutOpeningHoursDTO);
  NewOpeningHoursDTO openingHoursDTOToNewOpeningHoursDTO(OpeningHoursDTO openingHoursDTO, Location existingLocation);
}

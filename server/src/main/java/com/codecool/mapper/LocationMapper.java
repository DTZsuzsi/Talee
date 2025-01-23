package com.codecool.mapper;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.LocationInEventDTO;
import com.codecool.DTO.location.LocationWithoutOpeningHoursDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.model.locations.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper

public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "adminUser", source = "adminUser")
    Location newLocationDTOToLocation(NewLocationDTO newLocationDTO);

    LocationInEventDTO locationToLocationInEventDTO(Location location);

    Location locationInEventDTOToLocation(LocationInEventDTO locationInEventDTO);

    LocationDTO locationToLocationDTO(Location location);

    LocationWithoutOpeningHoursDTO locationToLocationWithoutOpeningHoursDTO(Location location);

}

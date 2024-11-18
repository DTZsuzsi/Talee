package com.codecool.mapper;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.LocationWithoutOpeningHoursDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.model.locations.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper//(uses = {UserMapper.class}, componentModel = "spring")
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @Mapping(target = "adminUser", source = "adminUser")
    @Mapping(target = "openingHours", source = "openingHours")
    LocationDTO locationToLocationDTO(Location location);
    Location newLocationDTOToLocation(NewLocationDTO newLocationDTO);
    @Mapping(target = "adminUser", source = "adminUser")
    LocationWithoutOpeningHoursDTO locationToLocationWithoutOpeningHoursDTO(Location location);
}

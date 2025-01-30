package com.codecool.talee.repository;

import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean deleteLocationById(long id);
    List<Location> findAllByTagsContaining(Tag tag);
    Location findLocationByNameAndAddress(String name, String address);
}

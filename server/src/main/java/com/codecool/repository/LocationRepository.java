package com.codecool.repository;

import com.codecool.model.locations.Location;
import com.codecool.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    long deleteLocationById(long id);
    List<Location> findAllByTagsContaining(Tag tag);
}

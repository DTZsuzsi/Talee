package com.codecool.talee.repository;

import com.codecool.talee.model.events.Event;
import com.codecool.talee.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventById(long id);
    List<Event> findAllByLocationId(long locationId);
    List<Event> findEventsByTagsContaining(Tag tag);
    void deleteEventById(long id);
}

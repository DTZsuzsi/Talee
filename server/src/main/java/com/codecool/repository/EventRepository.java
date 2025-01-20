package com.codecool.repository;

import com.codecool.model.events.Event;
import com.codecool.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById(long id);
    List<Event> findAllByLocationId(long locationId);
    List<Event> findEventsByTagsContaining(Tag tag);
    void deleteEventById(long id);
}

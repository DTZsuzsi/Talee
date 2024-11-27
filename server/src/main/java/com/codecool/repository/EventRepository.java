package com.codecool.repository;

import com.codecool.model.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
Event findEventById(long id);
boolean deleteEventById(long id);
List<Event> findAllByLocationId(long locationId);

}

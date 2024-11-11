package com.codecool.repository;

import com.codecool.model.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
Event findEventById(int id);
boolean deleteEventById(int id);

}

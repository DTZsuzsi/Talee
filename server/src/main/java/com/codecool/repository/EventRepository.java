package com.codecool.repository;

import com.codecool.model.events.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, Integer> {
Event findEventById(int id);
boolean deleteEventById(int id);

}

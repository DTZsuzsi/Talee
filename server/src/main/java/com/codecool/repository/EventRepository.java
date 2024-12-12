package com.codecool.repository;

import com.codecool.model.events.Event;
import com.codecool.model.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById(long id);
    List<Event> findAllByLocationId(long locationId);
    @Modifying
    @Query("DELETE FROM  Event WHERE id = :id")
    void deleteEventById(@Param("id") long id);
    List<Event> findEventsByTagsContaining(Tag tag);
}

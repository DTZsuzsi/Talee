package com.codecool.DAO.eventsDAO;

import com.codecool.DAO.DatabaseConnection;
import com.codecool.model.events.Event;
import com.codecool.model.events.EventSize;
import com.codecool.model.events.EventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class EventsDAOJDBC2 implements EventsDAO {
    private DatabaseConnection databaseConnection;

    @Autowired
    public EventsDAOJDBC2(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Event getEventById(int id) {
        String sql = "SELECT id, date, name, description, location_id, owner, size, users, tags, status, created_at  FROM events WHERE id = ?";
        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement st = conn.prepareStatement(sql);) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int event_id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                String name = rs.getString("name");
                String location = rs.getString("description");
                int location_id = rs.getInt("location_id");
                String owner = rs.getString("owner");
                String size = rs.getString("size");
                Set<String> users = Set.of(rs.getString("users"));
                Set<String> tags = Set.of(rs.getString("tags"));
                String status = rs.getString("status");
                Timestamp created_at = rs.getTimestamp("created_at");

                return new Event(event_id, date, name, location, location_id, users, owner, findSize(size), tags, findStatus(status), created_at);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int createEvent(Event newEvent) {
        String sql = "INSERT INTO events(date,name, description, location_id, owner, size, tags, status,created_at) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = databaseConnection.getConnection();) {
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, Date.valueOf(newEvent.getDate()));
            st.setString(2, newEvent.getName());
            st.setString(3, newEvent.getDescription());
            st.setInt(4, newEvent.getLocation_id());
            st.setString(5, newEvent.getOwner());
            st.setString(6, newEvent.getSize().toString());
            String[] tagsArray = newEvent.getTags().toArray(new String[0]);
            st.setArray(7, conn.createArrayOf("varchar", tagsArray));
            st.setString(8, newEvent.getStatus().toString());
            st.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("No ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT id, date, name, description, location_id, owner, size, users, tags, status, created_at FROM events ORDER BY created_at DESC";

        try (Connection conn = databaseConnection.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {
            while (rs.next()) {
                int event_id = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                String name = rs.getString("name");
                String location = rs.getString("description");
                int location_id = rs.getInt("location_id");
                String owner = rs.getString("owner");
                String size = rs.getString("size");

                // For users
                Array sqlUserArray = rs.getArray("users");
                Set<String> users;

                if (sqlUserArray != null) {
                    String[] userArray = (String[]) sqlUserArray.getArray();
                    users = Set.of(userArray);
                } else {
                    users = Set.of("nobody");
                }
                Array sqlTagArray = rs.getArray("tags");
                Set<String> tags;

                if (sqlTagArray != null) {
                    String[] tagArray = (String[]) sqlTagArray.getArray();
                    tags = Set.of(tagArray);
                } else {
                    tags = Set.of();
                }

                String status = rs.getString("status");
                Timestamp created_at = rs.getTimestamp("created_at");

                Event event = new Event(event_id, date, name, location, location_id, users, owner, findSize(size), tags, findStatus(status), created_at);
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    @Override
    public boolean modifyEvent(Event updatedEvent) {
        String sql = "UPDATE events SET date = ?, name = ?, description = ?, location_id = ?, owner = ?, size = ?, users = ?, tags = ?, status = ? WHERE id = ?";

            try (Connection conn = databaseConnection.getConnection();
                 PreparedStatement st = conn.prepareStatement(sql)) {

                st.setDate(1, Date.valueOf(updatedEvent.getDate()));
                st.setString(2, updatedEvent.getName());
                st.setString(3, updatedEvent.getDescription());
                st.setInt(4, updatedEvent.getLocation_id());
                st.setString(5, updatedEvent.getOwner());
                st.setString(6, updatedEvent.getSize().toString());
                st.setArray(7, conn.createArrayOf("VARCHAR", updatedEvent.getUsers().toArray()));
                st.setArray(8, conn.createArrayOf("VARCHAR", updatedEvent.getTags().toArray()));
                st.setString(9, updatedEvent.getStatus().name());
                st.setInt(10, updatedEvent.getId());

                int rowsUpdated = st.executeUpdate();
                return rowsUpdated > 0;

            } catch (SQLException e) {
                throw new RuntimeException("Error updating event", e);
            }
        }

    @Override
    public boolean deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        try(Connection conn=databaseConnection.getConnection();
        PreparedStatement st=conn.prepareStatement(sql)){
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        }
        catch(SQLException e){
            throw new RuntimeException("Error deleting event", e);
        }
    }


    private EventSize findSize(String size) {
        EventSize[] eventSizes = EventSize.values();
        EventSize eventSize = null;
        for (EventSize eventSize1 : eventSizes) {
            if (eventSize1.equals(size)) {
                eventSize = eventSize1;
            }
        }
        return eventSize;
    }

    private EventStatus findStatus(String status) {
        EventStatus[] eventStatuses = EventStatus.values();
        EventStatus eventStatus = null;
        for (EventStatus eventStatus1 : eventStatuses) {
            if (eventStatus1.equals(status)) {
                eventStatus = eventStatus1;
            }
        }
        return eventStatus;
    }

}




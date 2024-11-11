//package com.codecool.DAO.locationDAO;
//
//import com.codecool.DAO.DatabaseConnection;
//import com.codecool.DTO.locationDTO.NewLocationDTO;
//import com.codecool.model.location.Location;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Repository
//public class LocationDaoJdbc implements LocationDAO {
//  private final DatabaseConnection databaseConnection;
//
//  @Autowired
//  public LocationDaoJdbc(DatabaseConnection databaseConnection) {
//    this.databaseConnection = databaseConnection;
//  }
//
//  //TODO implement CRUD methods
//
//  @Override
//  public Set<Location> getAllLocations() {
//    Set<Location> locations = new HashSet<>();
//    String sql = "SELECT * FROM locations";
//
//    try (Connection connection = databaseConnection.getConnection();
//         PreparedStatement ps = connection.prepareStatement(sql);
//         ResultSet rs = ps.executeQuery()) {
//      while (rs.next()) {
//        locations.add(new Location(
//                rs.getInt("id"),
//                rs.getString("name"),
//                rs.getString("address"),
//                rs.getString("phone"),
//                rs.getString("email"),
//                rs.getString("description")
//        ));
//      }
//
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//    return locations;
//  }
//
//  @Override
//  public Location getLocationById(int id) {
//    String sql = "SELECT id, name, address, phone, email, description FROM locations WHERE id = ?";
//
//    try (Connection connection = databaseConnection.getConnection();
//         PreparedStatement ps = connection.prepareStatement(sql);) {
//      ps.setInt(1, id);
//      ResultSet rs = ps.executeQuery();
//      if (rs.next()) {
//        return new Location(
//                rs.getInt("id"),
//                rs.getString("name"),
//                rs.getString("address"),
//                rs.getString("phone"),
//                rs.getString("email"),
//                rs.getString("description")
//        );
//      }
//
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//    return null;
//  }
//
//  @Override
//  public int addLocation(NewLocationDTO location) {
//    String sql = "INSERT INTO locations (name, address, phone, email, website, facebook, instagram, description) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//    try (Connection connection = databaseConnection.getConnection();
//         PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
//      ps.setString(1, location.name());
//      ps.setString(2, location.address());
//      ps.setString(3, location.phone());
//      ps.setString(4, location.email());
//      ps.setString(5, location.website());
//      ps.setString(6, location.facebook());
//      ps.setString(7, location.instagram());
//      ps.setString(8, location.description());
//      ps.executeUpdate();
//
//      try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//        if (generatedKeys.next()) {
//          return generatedKeys.getInt(1);
//        } else {
//          throw new SQLException("No ID obtained.");
//        }
//      }
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//  }
//
////  @Override
////  public boolean updateLocation(LocationDTO location) {
////    return false;
////  }
////
////  @Override
////  public boolean deleteLocation(int id) {
////    return false;
////  }
//}

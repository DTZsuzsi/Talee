//package com.codecool.DAO;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class DatabaseConnection {
//    private final String databaseUrl;
//    private final String databaseUser;
//    private final String databasePassword;
//
//    public DatabaseConnection(String databaseUrl, String databaseUser, String databasePassword) {
//        this.databaseUrl = databaseUrl;
//        this.databaseUser = databaseUser;
//        this.databasePassword = databasePassword;
//    }
//
//    public Connection getConnection() throws SQLException {
//        Properties props = new Properties();
//        props.setProperty("user", databaseUser);
//        props.setProperty("password", databasePassword);
//        return DriverManager.getConnection(databaseUrl, props);
//    }
//}

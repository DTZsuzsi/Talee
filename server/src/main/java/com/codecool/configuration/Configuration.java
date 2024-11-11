package com.codecool.configuration;


import com.codecool.DAO.DatabaseConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    //    TODO: Add the url of your database to the Environment Variables of the Run Configuration
    @Value("${database.url}")
    private String databaseUrl;
    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Bean
    public DatabaseConnection databaseConnection() {
        return new DatabaseConnection(databaseUrl, databaseUsername, databasePassword);
    }

}


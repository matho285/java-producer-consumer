package com.mmaksin.java;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager {
    private static Properties properties;

    private static final String CREATE_TABLE_SQL = """
            create table if not exists susers (\r
              user_id int primary key,\r
              user_guid varchar(50),\r
              user_name varchar(50));""";

    public DatabaseManager() {
        properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Properties not found.");
        }
        createTableIfMissing();
    }

    public void createTableIfMissing() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        String jdbcURL = properties.getProperty("jdbc.url");
        String jdbcUsername = properties.getProperty("jdbc.user");
        String jdbcPassword = properties.getProperty("jdbc.password");

        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}

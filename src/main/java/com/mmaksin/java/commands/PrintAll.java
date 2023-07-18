package com.mmaksin.java.commands;

import com.mmaksin.java.Command;
import com.mmaksin.java.DatabaseManager;
import com.mmaksin.java.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintAll implements Command {

    private final DatabaseManager databaseManager;
    private static final Logger LOG = LoggerFactory.getLogger(PrintAll.class);

    public PrintAll(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void execute() {
        try (Connection connection = databaseManager.getConnection(); PreparedStatement selectStatement = connection.prepareStatement("select * from susers")) {
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String userGuid = resultSet.getString("user_guid");
                String userName = resultSet.getString("user_name");

                User user = new User(id, userGuid, userName);
                System.out.println(user);
            }
        } catch (SQLException e) {
            LOG.error("SQL State: {}\n{}", e.getSQLState(), e.getMessage());
        }
    }
}

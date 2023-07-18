package com.mmaksin.java.commands;

import com.mmaksin.java.Command;
import com.mmaksin.java.DatabaseManager;
import com.mmaksin.java.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add implements Command {

    private final DatabaseManager databaseManager;
    private final User user;
    private static final Logger LOG = LoggerFactory.getLogger(Add.class);

    public Add(DatabaseManager databaseManager, User user) {
        this.databaseManager = databaseManager;
        this.user = user;
    }

    private static final String INSERT_USERS_SQL = "INSERT INTO susers" +
            " (user_id, user_guid, user_name) VALUES" +
            " (?, ?, ?);";

    @Override
    public void execute() {
        try (Connection connection = databaseManager.getConnection(); PreparedStatement selectStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            selectStatement.setInt(1, user.getId());
            selectStatement.setString(2, user.getGuid());
            selectStatement.setString(3, user.getUsername());

            selectStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQL State: {}\n{}", e.getSQLState(), e.getMessage());
        }
    }
}

package com.mmaksin.java.commands;

import com.mmaksin.java.Command;
import com.mmaksin.java.DatabaseManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete implements Command {

    private final DatabaseManager databaseManager;
    private static final Logger LOG = LoggerFactory.getLogger(Delete.class);

    public Delete(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void execute() {
        try (Connection connection = databaseManager.getConnection(); PreparedStatement selectStatement = connection.prepareStatement("delete from susers")) {
            selectStatement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("SQL State: {}\n{}", e.getSQLState(), e.getMessage());
        }
    }
}

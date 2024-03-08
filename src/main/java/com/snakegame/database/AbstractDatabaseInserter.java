package com.snakegame.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractDatabaseInserter {

    protected abstract String getInsertSQL();

    protected abstract void setParameters(PreparedStatement statement) throws SQLException;

    public void insert() {
        String sql = getInsertSQL();

        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setParameters(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert operation failed: " + e.getMessage());
        }
    }
}
package com.snakegame.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HighScoreInserter extends AbstractDatabaseInserter {

    private String username;
    private int score;

    private Timestamp timestamp;

    public HighScoreInserter(String username, int score, Timestamp timestamp) {
        this.username = username;
        this.score = score;
        this.timestamp = timestamp;
    }

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO highscores (username, score, timestamp) VALUES (?, ?, ?)";
    }

    @Override
    protected void setParameters(PreparedStatement statement) throws SQLException {
        statement.setString(1, username);
        statement.setInt(2, score);
        statement.setTimestamp(3, timestamp);
    }
}
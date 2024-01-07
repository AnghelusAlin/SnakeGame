package com.snakegame.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameState {
    private Map<String, Player> players;
    int gridWidth = 160;
    int gridHeight = 90;

    public GameState() {
        this.players = new HashMap<>();
        gridWidth = 160;
        gridHeight = 90;
    }
    public void addPlayer(String name, int gridWidth, int gridHeight) {
        Player player = new Player(name);

        // Add the player to the grid
        int minX = 5;
        int minY = 5;
        int maxX = gridWidth - 5;
        int maxY = gridHeight - 5;

        List<Position> positions = generateRandomPositions(minX, minY, maxX, maxY);

        player.setPositions(positions);

        players.put(name, player);
        System.out.println("New player added:");
        player.printPlayer();
    }
    private List<Position> generateRandomPositions(int minX, int minY, int maxX, int maxY) {
        List<Position> randomPositions = new ArrayList<>();

        // Generate random position
        int randomX = (int) (Math.random() * (maxX - minX + 1) + minX);
        int randomY = (int) (Math.random() * (maxY - minY + 1) + minY);
        Position randomPosition = new Position(randomX, randomY);
        randomPositions.add(randomPosition);

        // Generate two positions below the random position
        Position positionBelow1 = new Position(randomX, randomY + 1);
        Position positionBelow2 = new Position(randomX, randomY + 2);
        randomPositions.add(positionBelow1);
        randomPositions.add(positionBelow2);

        return randomPositions;

    }
    public void updatePlayerDirection(String name, Direction direction) {
        if (players.containsKey(name)) {
            Player player = players.get(name);
            player.setCurrentDirection(direction);
            players.put(name, player);
        }
    }
    public boolean isOpen(Position position) {
        // Check if the position is occupied by any player
        for (Player player : players.values()) {
            if (player.getPositions().contains(position)) {
                return false;
            }
        }
        return true;
    }
    public void updateGameState() {
        List<String> playersToRemove = new ArrayList<>();

        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();

            if (player.updatePositions(gridWidth, gridHeight)) {
                // Update the positions of the player
                players.put(player.getName(), player);
            } else {
                // Add the player to the removal list if the position is illegal
                playersToRemove.add(player.getName());
            }
        }

        // Remove players with illegal positions
        for (String playerName : playersToRemove) {
            players.remove(playerName);
        }
    }

    public String serialize() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"").append("playerData").append("\": [");
        for (Map.Entry<String, Player> entry : players.entrySet()) {
            Player player = entry.getValue();
            String playerJson = player.serializeToJson();
            jsonBuilder.append(playerJson).append(",");
        }
        if (!players.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove the extra comma if map is not empty
        }
        jsonBuilder.append("]");
        jsonBuilder.append("}");
        return jsonBuilder.toString();
    }
}

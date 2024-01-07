package com.snakegame.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class GameState {
    private Map<String, Player> players;

    public GameState() {
        this.players = new HashMap<>();
    }

    public void addPlayer(String name, String id, int gridWidth, int gridHeight) {
        Player player = new Player(name, id);

        // Add the player to the grid
        int minX = 5;
        int minY = 5;
        int maxX = gridWidth - 5;
        int maxY = gridHeight - 5;

        List<Position> positions = generateRandomPositions(minX, minY, maxX, maxY);

        player.setPositions(positions);

        players.put(player.getId(), player);
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

    public boolean isOpen(Position position){
        // Check if the position is occupied by any player
        for (Player player : players.values()) {
            if (player.getPositions().contains(position)) {
                return false;
            }
        }
        return true;
    }

    public void updateGameState(Direction direction, int gridWidth, int gridHeight) {
        // Update positions for each player
        for (Player player : players.values()) {
            if(player.updatePositions(direction, gridWidth, gridHeight)){
                // Update the positions of the player
                players.put(player.getId(), player);
            } else {
                // Remove the player if the position is illegal
                players.remove(player.getId());
            }
        }
    }
}
package com.snakegame.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameState {
    private List<Player> players;

    public GameState() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void updateGameState(String direction, int gridWidth, int gridHeight) {
        // Update positions for each player
        for (Player player : players) {
            player.updatePositions(direction, gridWidth, gridHeight);
        }

        // Check for player collisions
        checkPlayerCollisions();
    }

    private void checkPlayerCollisions() {
        for (Player currentPlayer : players) {
            // Get the head position of the current player
            Position currentHead = currentPlayer.getPositions().get(0);

            // Check for collisions with other players' heads
            for (Player otherPlayer : players) {
                if (!currentPlayer.equals(otherPlayer)) { // Exclude current player
                    Position otherHead = otherPlayer.getPositions().get(0);
                    if (currentHead.equals(otherHead)) {
                        // Collision detected, clear positions of the collided player
                        otherPlayer.getPositions().clear();
                    }
                }
            }
        }
    }
}
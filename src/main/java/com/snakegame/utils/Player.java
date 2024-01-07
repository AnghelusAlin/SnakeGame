package com.snakegame.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Player {
    private String name;
    private List<Position> positions;
    private String color;
    public void updatePositions(String direction, int gridWidth, int gridHeight) {
        // Store the current head position
        Position currentHead = positions.get(0);
        Position newHead = new Position(currentHead.getX(), currentHead.getY());

        // Move the head in the specified direction
        switch (direction) {
            case "a":
                newHead.setX(newHead.getX() - 1);
                break;
            case "d":
                newHead.setX(newHead.getX() + 1);
                break;
            case "w":
                newHead.setY(newHead.getY() - 1);
                break;
            case "s":
                newHead.setY(newHead.getY() + 1);
                break;
            // Add more cases for other directions if needed
        }

        // Check if the new head position is legal
        if (isValidPosition(newHead, gridWidth, gridHeight)) {
            // Move the rest of the positions to form a snake
            List<Position> newPositions = new ArrayList<>();
            newPositions.add(newHead);
            newPositions.addAll(positions.subList(0, positions.size() - 1));

            // Update the positions
            positions = newPositions;
        } else {
            // Illegal position, empty the position list
            positions.clear();
        }
    }

    private boolean isValidPosition(Position position, int gridWidth, int gridHeight) {
        // Check if the position is within the defined grid
        return position.getX() >= 0 && position.getX() < gridWidth &&
                position.getY() >= 0 && position.getY() < gridHeight &&
                // Check if the position does not collide with itself
                !positions.contains(position);
    }
}

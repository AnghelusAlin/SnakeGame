package com.snakegame.utils;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {

    private String name;
    private String id;
    private List<Position> positions;
    private String color;
    private Direction currentDirection = Direction.RIGHT;
    Player(String name, String id) {
        this.name = name;
        this.id = id;
        this.positions = new ArrayList<>();
        this.color = Colors.values()[(int) (Math.random() * Colors.values().length)].toString();
    }
    public boolean updatePositions(Direction direction, int gridWidth, int gridHeight) {
        // Store the current head position
        Position currentHead = positions.get(0);
        Position newHead = new Position(currentHead.getX(), currentHead.getY());

        // Move the head in the specified direction
        switch (direction) {
            case LEFT:
                newHead.setX(newHead.getX() - 1);
                break;
            case RIGHT:
                newHead.setX(newHead.getX() + 1);
                break;
            case UP:
                newHead.setY(newHead.getY() - 1);
                break;
            case DOWN:
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
            return true;
        } else {
            // Illegal position, empty the position list
            positions.clear();
            return false;
        }
    }

    private boolean isValidPosition(Position position, int gridWidth, int gridHeight) {
        // Check if the position is within the defined grid
        return position.getX() >= 0 && position.getX() < gridWidth &&
                position.getY() >= 0 && position.getY() < gridHeight &&
                // Check if the position does not collide with itself
                !positions.contains(position);
                //TODO Check if the position does not collide with other players
    }

    public void printPlayer() {
        System.out.println("Player:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Color: " + color);
        System.out.println("Current Direction: " + currentDirection);
        System.out.println("Positions:");
        for (Position position : positions) {
            System.out.println("  X: " + position.getX() + ", Y: " + position.getY());
        }
        System.out.println("------");
    }
}

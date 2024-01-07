package com.snakegame.web;

import com.snakegame.utils.Direction;
import com.snakegame.utils.GameState;
import com.snakegame.utils.dtos.DirectionMessage;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final GameService gameService;

    public GameState gameState = new GameState();
    public int counter = 0;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @MessageMapping("/snake-game")
    public void addPlayer(String payload) {
        // Update game logic...
        // After updating the game state, broadcast it to all players


        JSONObject jsonObject = new JSONObject(payload);
        String name = jsonObject.getString("content");

        gameState.addPlayer(name, 160, 90);

    }

    @MessageMapping("/move") // Endpoint to receive movement commands from players
    public void movePlayer(String payload) {

        //System.out.println(payload);
        JSONObject jsonObject = new JSONObject(payload);
        String name = jsonObject.getString("name");
        String message = jsonObject.getString("content");
        Direction dir = switch (message) {
            case "w" -> Direction.UP;
            case "a" -> Direction.LEFT;
            case "s" -> Direction.DOWN;
            default -> Direction.RIGHT;
        };
        gameState.updatePlayerDirection(name, dir);
        //System.out.println(gameState.serialize());
        counter = 0;
        gameState.updateGameState();
        gameService.broadcastGameState(gameState.serialize());
        // Process the received movement command and update the game state accordingly
        // Fetch the player, update the position, and generate the new game state
    }
}

package com.snakegame.web;

import com.snakegame.utils.Direction;
import com.snakegame.utils.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @MessageMapping("/update-game")
    public void updateGameState(String gameStateJson) {
        // Update game logic...

        // After updating the game state, broadcast it to all players
        gameService.broadcastGameState(gameStateJson);
    }
    @MessageMapping("/move") // Endpoint to receive movement commands from players
    public void movePlayer(Direction direction) {
        // Process the received movement command and update the game state accordingly
        // Fetch the player, update the position, and generate the new game state
        GameState gameState = new GameState();

    }
}

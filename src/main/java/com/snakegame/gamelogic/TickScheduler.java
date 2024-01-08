package com.snakegame.gamelogic;

import com.snakegame.utils.GameState;
import com.snakegame.web.GameController;
import com.snakegame.web.GameService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TickScheduler {
    private GameController gameController;
    private int elongationCounter = 0;
    @Autowired
    public TickScheduler(GameController gameController) {
        this.gameController = gameController;
    }
    // Execute the method every second (1000ms)
    @Scheduled(fixedRate = 250)
    public void tick() {
        elongationCounter++;
        if(elongationCounter >= 20){
            elongationCounter = 0;
            gameController.getGameState().updateGameState(true);
        }else{
            gameController.getGameState().updateGameState(false);
        }

        gameController.getGameState().printPlayers();
        gameController.getGameService().broadcastGameState(gameController.getGameState().serialize());
    }
}

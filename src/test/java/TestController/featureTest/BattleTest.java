package TestController.featureTest;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.turns.TurnOwner;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.Goomba;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {


    GameController controller;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setPlayer("Ernesto Sábato");
    }

    /*
    Asserts that the battle is lost when the player characters are both KO.

    Tests the following requisites:



     */
    @Test
    public void battleLostTest() throws IOException {
        // Battle Begins
        controller.createAndSetNewBattle();

        // let's pass until all enemies are KO
        while(!controller.isGameFinished()) {
            // Player passes and finishes turn
            controller.selectTurnKind("passing");
            controller.startCurrentTurn();
            controller.finishTurn();
            // enemy turn
            controller.startCurrentTurn();
            controller.finishTurn();
        }

        assertTrue(controller.getPlayer().isPlayerKO());

        assertTrue(controller.isGameFinished());
        // winner false -> assert the player has lost.
        assertFalse(controller.getWinner());

    }


}

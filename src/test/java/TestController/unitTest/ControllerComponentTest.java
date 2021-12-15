package TestController.unitTest;

import aventurasdemarcoyluis.backend.controller.GameController;

import aventurasdemarcoyluis.backend.model.items.ItemType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class ControllerComponentTest {
    GameController controller;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setPlayer("Ernesto Sábato");
    }

    @Test
    public void addItemToPlayerTest(){
        // The battle hasn't started yet, thus, the vault should be empty
        assertEquals(0,controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        controller.addItemToPlayer(ItemType.HONEYSYRUP);
        assertEquals(1,controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
    }


    @Test
    public void playerWinningSequenceTest(){
        controller.playerWinningSequence();
        assertTrue(controller.getPlayerWon());
    }

    @Test
    public void playerLoosingSequenceTest(){
        controller.playerLosingSequence();
        assertFalse(controller.getPlayerWon());
    }

    @Test
    public void playerLvlUpTest(){
        // Lets assume the player has won more than one battle
        controller.getPlayer().increaseBattleNumber();
        assertEquals(1,controller.getPlayer().getPlayerLvl());

        controller.playerLvlUp();
        assertEquals(2,controller.getPlayer().getPlayerLvl());
    }

}

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

}

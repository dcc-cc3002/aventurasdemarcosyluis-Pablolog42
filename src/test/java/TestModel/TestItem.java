package TestModel;



import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.Player;
import aventurasdemarcoyluis.backend.model.enemies.Goomba;
import aventurasdemarcoyluis.backend.model.enemies.Spiny;
import aventurasdemarcoyluis.backend.model.items.HoneySyrup;
import aventurasdemarcoyluis.backend.model.items.ItemVault;
import aventurasdemarcoyluis.backend.model.items.RedMushroom;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static aventurasdemarcoyluis.backend.model.items.ItemType.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestItem {

    private Spiny testSpiny;
    private Marco testMarco;
    private Luis testLuis;
    private Goomba testGoomba;

    private Player jugador1;
    private Player jugador2;

    private HoneySyrup honeySyrup;
    private RedMushroom redMushroom;

    private GameController controller;

    @BeforeEach
    public void setUp() {
        controller = new GameController("j1");
        testGoomba= new Goomba(2,4,15,100,15);
        testMarco = new Marco(10,10,10,100,5,10,2);
        testLuis = new Luis(10,10,10,100,5,10,2);
        testSpiny = new Spiny(10,1,0,100,1);
    }

    @Test
    public void testHoneySyrup() throws InvalidSelectionException {
        controller.getPlayer().addAnItem(HONEYSYRUP,2);
        controller.getPlayer().tryToUseItem(HONEYSYRUP,controller.getPlayerMainCharacter(EntityType.MARCO));
        controller.getPlayer().tryToUseItem(HONEYSYRUP,controller.getPlayerMainCharacter(EntityType.LUIS));

        assertEquals(13,controller.getPlayerMainCharacter(EntityType.LUIS).getFp()); // restores 10% of maxHP (in this case 2FP)
        assertEquals(13, controller.getPlayerMainCharacter(EntityType.MARCO).getFp());
    }

    @Test
    public void testRedMushroom() throws InvalidSelectionException {
        controller.getPlayer().addAnItem(REDMUSHROOM,2);
        controller.getPlayer().tryToUseItem(REDMUSHROOM,controller.getPlayerMainCharacter(EntityType.MARCO));
        controller.getPlayer().tryToUseItem(REDMUSHROOM,controller.getPlayerMainCharacter(EntityType.LUIS));

        assertEquals(12,controller.getPlayerMainCharacter(EntityType.MARCO).getHp()); // restores 10% of maxHP (in this case 2HP)
        assertEquals(12,controller.getPlayerMainCharacter(EntityType.LUIS).getHp()); // restores 10% of maxHP (in this case 2HP)

    }

    @Test
    public void testNoItem() throws InvalidSelectionException {

        controller.createAndSetNewBattle();

        // We use 3 of each items
        controller.getPlayer().tryToUseItem(REDMUSHROOM,controller.getPlayerMainCharacter(EntityType.MARCO));
        controller.getPlayer().tryToUseItem(REDMUSHROOM,controller.getPlayerMainCharacter(EntityType.LUIS));
        controller.getPlayer().tryToUseItem(REDMUSHROOM,controller.getPlayerMainCharacter(EntityType.MARCO));

        // We use 3 of each items
        controller.getPlayer().tryToUseItem(HONEYSYRUP,controller.getPlayerMainCharacter(EntityType.LUIS));
        controller.getPlayer().tryToUseItem(HONEYSYRUP,controller.getPlayerMainCharacter(EntityType.MARCO));
        controller.getPlayer().tryToUseItem(HONEYSYRUP,controller.getPlayerMainCharacter(EntityType.LUIS));


        Exception e2 = assertThrows(InvalidSelectionException.class, () -> {
            controller.getPlayer().tryToUseItem(REDMUSHROOM,controller.getPlayerMainCharacter(EntityType.MARCO));
        });

        Exception e3 = assertThrows(InvalidSelectionException.class, () -> {
            controller.getPlayer().tryToUseItem(HONEYSYRUP,controller.getPlayerMainCharacter(EntityType.LUIS));
        });

        // check the exception message.
        assertEquals("j1 doesn't have a/an Red Mushroom in their inventory!" ,e2.getMessage());
        assertEquals("j1 doesn't have a/an Honey Syrup in their inventory!" ,e3.getMessage());


        ItemVault emptyVault = new ItemVault();
        // Jugador1 should have no items left in their inventory!
        assertEquals(controller.getPlayer().getPlayerVault(), emptyVault);
    }

}
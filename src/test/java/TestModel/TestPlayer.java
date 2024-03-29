package TestModel;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.Player;
import aventurasdemarcoyluis.backend.model.enemies.Boo;
import aventurasdemarcoyluis.backend.model.enemies.Goomba;
import aventurasdemarcoyluis.backend.model.enemies.Spiny;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {
    private Goomba testGoomba;
    private Boo testBoo;
    private Marco testMarco;
    private Luis testLuis;
    private Spiny testSpiny;
    private GameController controller;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15);

        testBoo = new Boo(100,10,100,1000,100);

        testMarco = new Marco(10,10,10,1000,10,1000,2);

        testSpiny = new Spiny(10,1,0,100,1);

        testLuis = new Luis(1,1,1,1,1000,100000,1);

        controller = new GameController();
        controller.setPlayer("Gabriel B.");
        // we set the first battle
        controller.runFirstBattle();
        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));
    }


    @Test
    public void hammerAttackTest() throws InvalidAttackException {

        assertEquals(100, testBoo.getHp(), 0.001);

        testMarco.hammerAttack(testGoomba);
        boolean expected = testGoomba.getHp() == 7.5 || testGoomba.getHp() == 15; // hammerAttack can fail
        assertTrue(expected);
    }

    @Test
    public void enemyAttackingTest(){
        testGoomba.attack(testMarco);// A normal attack with k=0.75 should do 0.15 dmg to marco
        assertEquals(7.75,testMarco.getHp());
    }
    @Test
    public void luisTest(){
        assertEquals(1,testLuis.getMaxFP());
        testLuis.enemyAttacking(testGoomba);
        assertEquals(977.5,testLuis.getHp());

        // this should fail at compiling, because Luis can't attack Boo.
//        testLuis.jumpAttack(testBoo);

        testLuis.setHp(1000);
        testLuis.enemyAttacking(testBoo);
        assertEquals(0,testLuis.getHp());
        assertTrue(testLuis.isKO());

        testLuis.setHp(1000);
        testLuis.enemyAttacking(testSpiny);
        // Luis is KO and can't receive further damage.
        assertEquals(1000,testLuis.getHp());
    }
    @Test
    public void marcoTest(){

        testMarco.setHp(1000);

        testGoomba.attack(testMarco);

        assertEquals(997.75,testMarco.getHp());

        testMarco.setHp(1000);

        // Boo can't attack Marco, thus this should fail at compiling
        // testBoo.attack(testMarco);

        assertEquals(1000,testMarco.getHp());

        testSpiny.attack(testMarco);
        assertEquals(999.25,testMarco.getHp());
    }


    @Test
    public void levelUpTest(){
        // Let's assume the player has already fought a battle
        controller.getPlayer().increaseBattleNumber();

        assertEquals(1,controller.getPlayer().getPlayerLvl());

        controller.getPlayer().lvlUp();


        // One of each item should be added.
        assertEquals(4,controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        assertEquals(4,controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));

        // Aumento de hp y fp acorde a especificaciones
        assertEquals(23,controller.getPlayerMainCharacter(EntityType.MARCO).getHp());
        assertEquals(23,controller.getPlayerMainCharacter(EntityType.LUIS).getHp());
        assertEquals(23,controller.getPlayerMainCharacter(EntityType.MARCO).getFp());
        assertEquals(23,controller.getPlayerMainCharacter(EntityType.LUIS).getFp());

    }


}

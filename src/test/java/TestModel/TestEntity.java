package TestModel;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.*;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntity {

    private Boo testBoo;
    private Marco testMarco;
    private Goomba testGoomba;
    private Luis testLuis;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15);
        testMarco = new Marco(10,10,10,100,10,10,2);
        testLuis = new Luis(10,10,10,100,10,10,2);

        testBoo = new Boo(10,1,0,100,1);
    }

    @Test
    public void hpKoSystemTest() throws InvalidAttackException {
        testMarco.jumpAttack(testBoo); // Should do 20 dmg
        assertEquals(0, testBoo.getHp(), 0.001); // Marco should kill Scary, as they only have 1 HP left. (Scary should have 0 HP instead of -19)
        assertTrue(testBoo.isKO()); // Thus, Scary should be dead :(
    }

    @Test
    public void damageTest(){
        testGoomba.receiveDamage(10);
        assertEquals(5, testGoomba.getHp());
        assertEquals(EntityType.GOOMBA,testGoomba.getType());

        testGoomba.restoreHP(1000);
        assertEquals(testGoomba.getMaxHP(),testGoomba.getHp());
    }

    @Test
    public void fPSystem(){
        testMarco.restoreFP(1000);
        assertEquals(100,testMarco.getFp());
        testMarco.setMaxFP(100000);
        assertEquals(100000,testMarco.getMaxFP());
    }

    @Test
    public void mainCharacterLvlUpTest(){
        testMarco.lvlUp();
        assertEquals((115.0 / 100) * 10, testMarco.getMaxHP());
        assertEquals(114,testMarco.getMaxFP());
//        assertEquals(100,testMarco.getFp());

    }

    @Test
    public void luisMainCharacterAttackTest() throws InvalidAttackException {
        testLuis.hammerAttack(testGoomba);
        testLuis.jumpAttack(testGoomba);

        // if hammer attack fails, the only attack that does dmg is the jump attack (5dmg)
        boolean correctResults = (testGoomba.getHp() == 2.5) || (testGoomba.getHp() == 10);

        assertTrue(correctResults);

    }



}

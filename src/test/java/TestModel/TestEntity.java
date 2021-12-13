package TestModel;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.*;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntity {

    private Boo testBoo;
    private Marco testMarco;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15);
        testMarco = new Marco(10,10,10,100,10,10,2);
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
    }

    @Test
    public void fPSystem(){
        testMarco.restoreFP(1000);
        assertEquals(100,testMarco.getFp());
        testMarco.setMaxFP(100000);
        assertEquals(100000,testMarco.getMaxFP());
    }


}

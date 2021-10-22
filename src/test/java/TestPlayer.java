import aventurasdemarcoyluis.entities.enemies.Boo;
import aventurasdemarcoyluis.entities.enemies.Goomba;
import aventurasdemarcoyluis.entities.enemies.Spiny;
import aventurasdemarcoyluis.entities.maincharacters.Luis;
import aventurasdemarcoyluis.entities.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayer {
    private Goomba testGoomba;
    private Boo testBoo;
    private Marco testMarco;
    private Luis testLuis;
    private Spiny testSpiny;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15,20,1);

        testBoo = new Boo(100,10,100,1000,100, 100,1);

        testMarco = new Marco(10,10,10,1000,10,1000,2);

        testSpiny = new Spiny(10,1,0,100,1,10,1);

        testLuis = new Luis(1,1,1,1,1000,100000,1);
    }

    @Test
    public void hammerAttackTest(){
        testMarco.hammerAttack(testBoo); // Boo should dodge hammer Attack
        assertEquals(100, testBoo.getHp(), 0.001);

        testMarco.hammerAttack(testGoomba);
        boolean expected = testGoomba.getHp() == 7.5 || testGoomba.getHp() == 15; // hammerAttack can fail
        assertTrue(expected);
    }

    @Test
    public void enemyAttackingTest(){
        testGoomba.attack(testMarco);// A normal attack with k=0.75 should do 0.15 dmg to marco
        assertEquals(9.85,testMarco.getHp());
    }
    @Test
    public void luisTest(){
        assertEquals(1,testLuis.getMaxFP());
        testLuis.goombaAttacking(testGoomba);
        assertEquals(998.5,testLuis.getHp());

        // this should fail at compiling, because Luis can't attack Boo.
        //testLuis.jumpAttack(testBoo);

        testLuis.setHp(1000);
        testLuis.booAttacking(testBoo);
        assertEquals(925,testLuis.getHp());

        testLuis.setHp(1000);
        testLuis.spinyAttacking(testSpiny);
        assertEquals(992.5,testLuis.getHp());
    }
    @Test
    public void marcoTest(){
        testMarco.setHp(1000);
        testMarco.goombaAttacking(testGoomba);
        assertEquals(999.85,testMarco.getHp());

        testMarco.setHp(1000);

        // Boo can't attack Marco, thus this should fail at compiling
        // testBoo.attack(testMarco);

        assertEquals(1000,testMarco.getHp());

        testMarco.spinyAttacking(testSpiny);
        assertEquals(999.25,testMarco.getHp());


    }
}

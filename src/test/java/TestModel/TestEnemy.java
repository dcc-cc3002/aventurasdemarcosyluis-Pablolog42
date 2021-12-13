package TestModel;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.Boo;
import aventurasdemarcoyluis.backend.model.enemies.Goomba;
import aventurasdemarcoyluis.backend.model.enemies.Spiny;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestEnemy {
    private Goomba testGoomba;
    private Boo testBoo;
    private Marco testMarco;
    private Spiny testSpiny;
    private Luis testLuis;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,1);

        testBoo = new Boo(100,10,100,1000,1);

        testMarco = new Marco(10,10,10,1000,10,10,2);

        testSpiny = new Spiny(10,1,1,100,1);

        testLuis = new Luis(10,10,10,10,10,10,10);
    }

    @Test
    public void constructorTest(){
        assertEquals(EntityType.BOO,testBoo.getType());
        assertEquals(EntityType.GOOMBA,testGoomba.getType());
    }

    @Test
    public void jumpAttackTest() throws InvalidAttackException {
        testMarco.jumpAttack(testBoo); // Should do 2 dmg
        assertEquals(98, testBoo.getHp(), 0.001);
    }


    @Test
    public void booDodgesHammerAttackException() {
        assertThrows(InvalidAttackException.class, () -> {
            testBoo.playerHammerAttacking(testMarco); // Boo should dodge hammer Attack, and throw and exception by design
        });

        assertEquals(100, testBoo.getHp(), 0.001);
    }

    @Test
    public void hammerAttackingTest() throws InvalidAttackException {




        testGoomba.playerHammerAttacking(testMarco); // This attack should always deal dmg, as the fail logic is programed onto the first double dispatch call.
        boolean expectedFirstAttack = testGoomba.getHp() == 7.5; // hammerAttack can fail
        testGoomba.playerHammerAttacking(testMarco);
        boolean expectedSecondAttack = testGoomba.getHp() == 0 && testGoomba.isKO(); // after 15 dmg points, testGoomba should be dead :(
        assertTrue(expectedFirstAttack && expectedSecondAttack);
    }

    @Test
    public void jumpAttackSpinyTest(){
        testSpiny.playerJumpAttacking(testMarco); // Should do no dmg to spiny, and self-inflict 5% of the maincharacters maxHP
        assertEquals(1, testSpiny.getHp(), 0.001); // Spiny's HP should not change
        assertEquals(9.5,testMarco.getHp(),0.001); // Marco's HP should decrease by 0.5
    }

    @Test
    public void booAttackTest() throws InvalidAttackException {
        testBoo.attack(testLuis);
    }

}

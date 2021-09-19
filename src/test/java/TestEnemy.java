import aventurasdemarcoyluis.*;


import aventurasdemarcoyluis.enemies.Boo;
import aventurasdemarcoyluis.enemies.Goomba;
import aventurasdemarcoyluis.enemies.Spiny;
import aventurasdemarcoyluis.players.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestEnemy {
    private Goomba testGoomba;
    private Boo testBoo;
    private Marco testMarco;
    private Spiny testSpiny;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15,20,1,"Goomba 1");

        testBoo = new Boo(100,10,100,1000,100, 100,1,"Boo1");

        testMarco = new Marco(10,10,10,1000,10,10,2,"Marco Polo");

        testSpiny = new Spiny(10,1,0,100,1,10,1,"Spiny Boy");
    }

    @Test
    public void constructorTest(){
        assertEquals(EntityType.BOO,testBoo.getType());
        assertEquals(EntityType.GOOMBA,testGoomba.getType());
    }

    @Test
    public void jumpAttackTest(){
        testMarco.jumpAttack(testBoo); // Should do 2 dmg
        assertEquals(98, testBoo.getHp(), 0.001);
    }


}

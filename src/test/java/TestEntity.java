import aventurasdemarcoyluis.*;


import aventurasdemarcoyluis.enemies.*;
import aventurasdemarcoyluis.players.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntity {

    private Boo testBoo;
    private Marco testMarco;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15,20,1,"Goomba 1");
        testMarco = new Marco(10,10,10,100,10,10,2,"Marco Polo");
        testBoo = new Boo(10,1,0,100,1,10,1,"Scary Boy");
    }

    @Test
    public void hpKoSystemTest(){
        testMarco.jumpAttack(testBoo); // Should do 20 dmg
        assertEquals(0, testBoo.getHp(), 0.001); // Marco should kill Scary, as they only have 1 HP left. (Scary should have 0 HP instead of -19)
        assertTrue(testBoo.isKO()); // Thus, Scary should be dead :(

        testBoo.restoreHP(3000); // Inyectamos epinefrina a Scary para que reviva. Tratamos de curar 3000 HP.
        assertFalse(testBoo.isKO()); // Veamos si revivió
        assertEquals(10, testBoo.getHp()); // Scary es chikito, y por tanto sólo deberia de recibir 10 HP como máx (su maxHP) de los 3000.
    }

    @Test
    public void damageTest(){
        testGoomba.receiveDamage(10);
        assertEquals(5, testGoomba.getHp());
        assertEquals(EntityType.GOOMBA,testGoomba.getType());
    }
}

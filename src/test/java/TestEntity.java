import aventurasdemarcoyluis.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntity {

    private Spiny testSpiny;
    private Marco testMarco;
    private Goomba testGoomba;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,15,20,1,"Goomba 1");
        testMarco = new Marco(10,10,10,10,10,2,"Marco Polo");
        testSpiny = new Spiny(10,1,0,1,10,1,"Spiny Boy");
    }

    @Test
    public void hpKoSystemTest(){
        testMarco.jumpAttack(testSpiny); // Should do 20 dmg
        assertEquals(0, testSpiny.getHp(), 0.001); // Marco should kill spiny, as they only have 1 HP left. (spiny should have 0 HP instead of -19)
        assertTrue(testSpiny.isKO()); // Thus, spiny should be dead :(

        testSpiny.restoreHP(3000); // Inyectamos epinefrina a Spiny para que reviva. Tratamos de curar 3000 HP.
        assertFalse(testSpiny.isKO()); // Veamos si revivió
        assertEquals(10, testSpiny.getHp()); // Spiny es chikito, y por tanto sólo deberia de recibir 10 HP como máx (su maxHP) de los 3000.
    }

    @Test
    public void damageTest(){
        testGoomba.receiveDamage(10);
        assertEquals(5, testGoomba.getHp());
        assertEquals(EntityType.GOOMBA,testGoomba.getType());
    }
}

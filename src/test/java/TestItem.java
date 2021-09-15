import aventurasdemarcoyluis.*;
import aventurasdemarcoyluis.enemies.Goomba;
import aventurasdemarcoyluis.enemies.Spiny;
import aventurasdemarcoyluis.items.HoneySyrup;
import aventurasdemarcoyluis.items.RedMushroom;
import aventurasdemarcoyluis.items.Star;
import aventurasdemarcoyluis.players.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestItem {

    private Spiny testSpiny;
    private Marco testMarco;
    private Goomba testGoomba;

    private Star star;
    private HoneySyrup honeySyrup;
    private RedMushroom redMushroom;

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,15,20,1,"Goomba 1");
        testMarco = new Marco(10,10,10,5,10,2,"Marco Polo");
        testSpiny = new Spiny(10,1,0,1,10,1,"Spiny Boy");

        star = new Star();
        honeySyrup = new HoneySyrup();
        redMushroom = new RedMushroom();

    }

    @Test
    public void testStar(){
        testMarco.useItem(star);
        assertTrue(testMarco.isInvincible());
    }
    @Test
    public void testHoneySyrup(){
        testMarco.useItem(honeySyrup);  // restores 3fp
        assertEquals(13,testMarco.getFp());
    }
    @Test
    public void testRedMushroom(){
        testMarco.useItem(redMushroom);
        assertEquals(6,testMarco.getHp()); // restores 10% of maxHP (in this case 1HP)
    }

}
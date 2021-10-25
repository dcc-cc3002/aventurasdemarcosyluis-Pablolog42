import aventurasdemarcoyluis.Player;
import aventurasdemarcoyluis.entities.enemies.Goomba;
import aventurasdemarcoyluis.entities.enemies.Spiny;
import aventurasdemarcoyluis.entities.items.HoneySyrup;
import aventurasdemarcoyluis.entities.items.ItemType;
import aventurasdemarcoyluis.entities.items.ItemVault;
import aventurasdemarcoyluis.entities.items.RedMushroom;
import aventurasdemarcoyluis.entities.maincharacters.Luis;
import aventurasdemarcoyluis.entities.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static aventurasdemarcoyluis.entities.items.ItemType.*;
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

    @BeforeEach
    public void setUp() {
        testGoomba= new Goomba(2,4,15,100,15,20,1);
        testMarco = new Marco(10,10,10,100,5,10,2);
        testLuis = new Luis(10,10,10,100,5,10,2);
        testSpiny = new Spiny(10,1,0,100,1,10,1);

        // By default, the values for Marco and Luis are: ATK 10, DEF 10, FP 10, MAXFP 100, HP 10, MAXHP 100, LVL 1
        jugador1 = new Player("Juan Radrigán",testMarco,testLuis);
        jugador2 = new Player("Zabaleta");

    }

    @Test
    public void testHoneySyrup(){
        jugador1.addAnItem(HONEYSYRUP);
        jugador1.useItem(HONEYSYRUP,jugador1.getLuis());  // restores 3fp to Juan Radrigans' Luis Main Character
        assertEquals(13,testLuis.getFp());
    }
    @Test
    public void testRedMushroom(){
        jugador1.addAnItem(REDMUSHROOM);
        jugador1.useItem(REDMUSHROOM,jugador1.getMarco());
        assertEquals(6,testMarco.getHp()); // restores 10% of maxHP (in this case 1HP)
    }
    @Test
    public void testNoItem(){
        Marco Marco1 = jugador1.getMarco();

        jugador1.useItem(REDMUSHROOM, Marco1);
        // This should print to screen that Marco has no "Red mushroom" item left, as he jus used the only one he had left.
        jugador1.useItem(HONEYSYRUP, Marco1);

        ItemVault emptyVault = new ItemVault();
        // Jugador1 should have no items left in their inventory!
        assertEquals(jugador1.getPlayerVault(), emptyVault);
    }

}
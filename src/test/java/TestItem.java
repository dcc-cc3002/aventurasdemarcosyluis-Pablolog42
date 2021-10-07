//import aventurasdemarcoyluis.entities.enemies.Goomba;
//import aventurasdemarcoyluis.entities.enemies.Spiny;
//import aventurasdemarcoyluis.entities.items.HoneySyrup;
//import aventurasdemarcoyluis.entities.items.ItemType;
//import aventurasdemarcoyluis.entities.items.RedMushroom;
//import aventurasdemarcoyluis.entities.items.Star;
//import aventurasdemarcoyluis.entities.maincharacters.Marco;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestItem {
//
//    private Spiny testSpiny;
//    private Marco testMarco;
//    private Goomba testGoomba;
//
//    private Star star;
//    private HoneySyrup honeySyrup;
//    private RedMushroom redMushroom;
//
//    @BeforeEach
//    public void setUp() {
//        testGoomba= new Goomba(2,4,15,100,15,20,1,"Goomba 1");
//        testMarco = new Marco(10,10,10,100,5,10,2,"Marco Polo");
//        testSpiny = new Spiny(10,1,0,100,1,10,1,"Spiny Boy");
//
//        star = new Star();
//        honeySyrup = new HoneySyrup();
//        redMushroom = new RedMushroom();
//
//        testMarco.addAnItem(star);
//        testMarco.addAnItem(honeySyrup);
//        testMarco.addAnItem(redMushroom);
//
//
//    }
//
//    @Test
//    public void testStar(){
//        testMarco.useItem(ItemType.STAR);
//        assertTrue(testMarco.isInvincible());
//    }
//    @Test
//    public void testHoneySyrup(){
//        testMarco.useItem(ItemType.HONEYSYRUP);  // restores 3fp
//        assertEquals(13,testMarco.getFp());
//    }
//    @Test
//    public void testRedMushroom(){
//        testMarco.useItem(ItemType.REDMUSHROOM);
//        assertEquals(6,testMarco.getHp()); // restores 10% of maxHP (in this case 1HP)
//    }
//    @Test
//    public void testNoItem(){
//        testMarco.useItem(ItemType.REDMUSHROOM);
//        // This should print to screen that Marco has no "Red mushroom" item left, as he jus used the only one he had left.
//        testMarco.useItem(ItemType.REDMUSHROOM);
//
//        testMarco.useItem(ItemType.STAR);
//        testMarco.useItem(ItemType.HONEYSYRUP);
//
//        // Marco should have no items left in their inventory!
//        assertEquals(new ArrayList<>(),testMarco.getArmamento());
//    }
//
//}
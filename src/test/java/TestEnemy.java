//import aventurasdemarcoyluis.entities.EntityType;
//import aventurasdemarcoyluis.entities.enemies.Boo;
//import aventurasdemarcoyluis.entities.enemies.Goomba;
//import aventurasdemarcoyluis.entities.enemies.Spiny;
//import aventurasdemarcoyluis.entities.maincharacters.Marco;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//public class TestEnemy {
//    private Goomba testGoomba;
//    private Boo testBoo;
//    private Marco testMarco;
//    private Spiny testSpiny;
//
//    @BeforeEach
//    public void setUp() {
//        testGoomba= new Goomba(2,4,15,100,15,20,1,"Goomba 1");
//
//        testBoo = new Boo(100,10,100,1000,100, 100,1,"Boo1");
//
//        testMarco = new Marco(10,10,10,1000,10,10,2,"Marco Polo");
//
//        testSpiny = new Spiny(10,1,0,100,1,10,1,"Spiny Boy");
//    }
//
//    @Test
//    public void constructorTest(){
//        assertEquals(EntityType.BOO,testBoo.getType());
//        assertEquals(EntityType.GOOMBA,testGoomba.getType());
//    }
//
//    @Test
//    public void jumpAttackTest(){
//        testMarco.jumpAttack(testBoo); // Should do 2 dmg
//        assertEquals(98, testBoo.getHp(), 0.001);
//    }
//
//    @Test
//    public void hammerAttackingTest(){
//        testBoo.playerHammerAttacking(testMarco); // Boo should dodge hammer Attack
//        assertEquals(100, testBoo.getHp(), 0.001);
//
//        testGoomba.playerHammerAttacking(testMarco); // This attack should always deal dmg, as the fail logic is programed onto the first double dispatch call.
//        boolean expectedFirstAttack = testGoomba.getHp() == 7.5; // hammerAttack can fail
//        testGoomba.playerHammerAttacking(testMarco);
//        boolean expectedSecondAttack = testGoomba.getHp() == 0 && testGoomba.isKO(); // after 15 dmg points, testGoomba should be dead :(
//        assertTrue(expectedFirstAttack && expectedSecondAttack);
//    }
//
//    @Test
//    public void jumpAttackSpinyTest(){
//        testSpiny.playerJumpAttacking(testMarco); // Should do no dmg to spiny, and self-inflict 5% of the maincharacters maxHP
//        assertEquals(1, testSpiny.getHp(), 0.001); // Spiny's HP should not change
//        assertEquals(9.5,testMarco.getHp(),0.001); // Marco's HP should decrease by 0.5
//    }
//
//
//}

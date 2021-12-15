package TestModel;
import aventurasdemarcoyluis.backend.controller.EnemyList;
import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEnemyList {

    public GameController controller;
    public EnemyList enemyList;

    @BeforeEach
    public void setUp(){
        controller = new GameController();
        controller.setPlayer("Pablo");

        enemyList = new EnemyList(controller);
    }

    @Test
    public void testListKO(){
        enemyList.addRandomEnemies(5);
        assertFalse(enemyList.isListKO());
    }

    @Test
    public void toStringTest(){
        // The list is empty
        assertSame("No enemies in enemy list", enemyList.toString());

        // we add an enemy
        InterEnemy boo = controller.createEnemy(EntityType.BOO,10,10,10,10,10);
        enemyList.addEnemy(boo);

        String a = "1. Boo with stats:   | ATK: 10.0 | DEF: 10.0 | HP: 10.0 | MAXHP: 10.0 | LVL: 10 |"+ "\n";

        assertEquals(a, enemyList.toString());
    }

}

package TestController;
import aventurasdemarcoyluis.backend.controller.turns.TurnOwner;
import aventurasdemarcoyluis.backend.model.enemies.EnemyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEnum {

    @Test
    public void turnOwnerTest(){
        assertEquals("Luis",TurnOwner.LUIS.toString());
        assertEquals("Enemy",TurnOwner.ENEMY.toString());
    }
    @Test
    public void enemyTypeTest(){
        assertEquals("Goomba", EnemyType.GOOMBA.toString());
        assertEquals("Boo",EnemyType.BOO.toString());
        assertEquals("Spiny",EnemyType.SPINY.toString());

    }

}

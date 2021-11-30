package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.turns.TurnOwner;
import aventurasdemarcoyluis.model.EntityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnOrderTest {

    GameController controller;

    @BeforeEach
    public void setUp(){
        controller = new GameController();
    }
    @Test
    public void turnOrderCalculatorTest(){
        // Assert the startup configuration is OK.
        assertSame( TurnOwner.MARCO, controller.getCurrentTurnOwner());
        assertSame( TurnOwner.LUIS, controller.getNextTurnOwner());


        // Let's forcefully remove one character forme the activeCharacter list (this will happen if, for example. one character results KO)
        controller.getActiveMainCharacterList().removeMainCharacterFromActiveList(EntityType.LUIS);

        // In this case, we removed luis from the turn chain. This means that all of luis's turns
        // on this battle Will be skipped.

        //As we started with a Marco turn, and skip luis's turn (as he is KO), the next turn should be the enemies turn.
        assertSame(TurnOwner.ENEMY, controller.calculateNextTurnOwner(controller.getCurrentTurnOwner()));

        // Let's try to finish the turn, to se how the turns changed.
        try {
            controller.finishTurn();
        }catch (Exception e){
            e.printStackTrace();
        }

        assertSame(TurnOwner.ENEMY, controller.getCurrentTurnOwner());
        assertSame(TurnOwner.MARCO,controller.getNextTurnOwner());


    }
}

package TestController.featureTest;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.turns.TurnOwner;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.Boo;
import aventurasdemarcoyluis.model.enemies.Goomba;
import aventurasdemarcoyluis.model.enemies.Spiny;
import aventurasdemarcoyluis.model.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {


    GameController controller;
    InterMainCharacter exampleMainCharacter;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setPlayer("Ernesto Sábato");
    }

    @Test
    public void constructorTest(){
        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
        assertNull(controller.getCurrentTurn());

    }

    @Test
    public void creationTest(){
        /* Assert that we can achieve the requirements:
             1. Create main characters.
             2. Create Enemies
             3. Create Items
             4. Create Item Vault
        */

        // Assert the constructor can create main characters
        assertTrue(controller.getPlayerMainCharacter(EntityType.MARCO) instanceof Marco);
        assertTrue(controller.getPlayerMainCharacter(EntityType.LUIS) instanceof Luis);

        // Assert that controller.createMainCharacter() can create main characters.
        exampleMainCharacter = controller.createMainCharacter(EntityType.MARCO,10,11,12,100,10,100,1);

        assertTrue(exampleMainCharacter instanceof Marco);
        assertEquals(10, exampleMainCharacter.getAtk());
        assertEquals(11, exampleMainCharacter.getDef());
        assertEquals(12, exampleMainCharacter.getHp());
        assertEquals(100, exampleMainCharacter.getMaxHP());

    }

    @Test
    public void turnsTest(){

        controller.selectTurnKind("attack");
        /* Assert that we can achieve the requirements:
             5. Implementar los turnos - parte de la implementation que define el turno actual.
             11. Obtener el personaje del turno actual. - getCurrentTurnOwner()
             12. Obtener Personaje del siguiente Turno (en este caso, se obtiene si es el turno del jugador, o del enemigo) - getNextTurnOwner()
        */
        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
        assertEquals(TurnType.ATTACK, controller.getCurrentTurn().getType());
        assertEquals(TurnOwner.ENEMY,controller.getNextTurnOwner());




    }

}

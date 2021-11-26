package TestController.featureTest;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.turns.*;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.Goomba;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {

    // TODO: Quitar verbose output para todos los tests  NullOutputStream para hacer un test silencioso (se vió en la clase 11)

    GameController controller;
    InterMainCharacter exampleMainCharacter;
    InterEnemy exampleGoomba;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setPlayer("Ernesto Sábato");
    }

    @Test
    public void constructorTest(){
        assertEquals(TurnOwner.MARCO,controller.getCurrentTurnOwner());
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
        assertEquals(10, exampleMainCharacter.getFp());
        assertEquals(100, exampleMainCharacter.getMaxFP());

        exampleGoomba = controller.createEnemy(EntityType.GOOMBA, 10,10,10,10,1);

        assertTrue(exampleGoomba instanceof Goomba);
        assertEquals(10, exampleGoomba.getAtk());
        assertEquals(10, exampleGoomba.getDef());
        assertEquals(10, exampleGoomba.getHp());
        assertEquals(10, exampleGoomba.getMaxHP());

    }



    @Test
    public void turnOrderTest() throws InvalidSelectionException, InvalidAttackException {
        controller.createAndSetNewBattle();
        controller.selectNewTurnKind(TurnType.PASSING);

        assertEquals(TurnOwner.MARCO,controller.getCurrentTurnOwner());
        assertEquals(TurnOwner.LUIS,controller.getNextTurnOwner());
        assertEquals(TurnType.PASSING, controller.getCurrentTurn().getType());
        // Marco Passes
        controller.startCurrentTurn();


        assertEquals(TurnOwner.LUIS,controller.getCurrentTurnOwner());
        assertEquals(TurnOwner.ENEMY,controller.getNextTurnOwner());

        controller.selectNewTurnKind(TurnType.PASSING);
        assertEquals(TurnType.PASSING, controller.getCurrentTurn().getType());

        controller.startCurrentTurn();



        // After luis makes their turn, the enemy turn will start inmediatly.
        // As the enemyTurn self-finshes, the new current turn owner
        // Should be the character that comes after the enemies attack.
        // As per the order: Marco -> Luis -> Enemy -> Marco -> Luis -> enemy ->...

        // after the enemy turn self-finishes, the current character should be marco.
        assertEquals(TurnOwner.MARCO,controller.getCurrentTurnOwner());
        assertEquals(TurnOwner.LUIS,controller.getNextTurnOwner());
        assertNull(controller.getCurrentTurn());

        // The enemy's turn should be executed immediately after luis's turn.






    }


    /* Assert that we can achieve the requirements:
         5. Create a turn (Attack turn)
         5. Implement the turn logic (player -> enemy -> player...)
         11. Get a turn's current main character (the one attacking or using an item)
         12. Get the next turn's main characters and enemies
         13. Finish the current turn
    */
    @Test
    public void attackTurnTest() throws IOException, InvalidSelectionException, InvalidAttackException {

        // Battle Begins
        controller.createAndSetNewBattle();

        // The player's vault should be created, and 3 items of each kind should be added to it.
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));


        controller.selectNewTurnKind(TurnType.ATTACK);

        /* Assert that we can achieve the requirements:
             5. Implementar los turnos - parte de la implementation que define el turno actual.
             11. Obtener el personaje del turno actual. - getCurrentTurnOwner()
             12. Obtener Personaje del siguiente Turno (en este caso, se obtiene si es el turno del jugador, o del enemigo) - getNextTurnOwner()
        */
        assertEquals(TurnOwner.MARCO,controller.getCurrentTurnOwner());
        assertEquals(TurnOwner.LUIS,controller.getNextTurnOwner());
        assertEquals(TurnType.ATTACK, controller.getCurrentTurn().getType());

        InterAttackTurn currentTurn = (InterAttackTurn) controller.getCurrentTurn();

        System.out.println(currentTurn.getEnemyList());

        // Marco Attacks the first enemy on the list
        currentTurn.setEnemyNumberToAttack(1);
        // Marco will make a jumpAttack
        currentTurn.setAttackType(AttackType.JUMP);

        // The turn finishes
        controller.finishTurn();


        // Now, it should be Luis's turn
        assertEquals(TurnOwner.LUIS, controller.getCurrentTurnOwner());

    }

    /* Assert that we can achieve the requirements:
      4. crear el baul de personajes principales
      5. Create a turn (Item turn)
      6. Que un jugador pueda usar un elemento del baul
      7. Obtener los elementos del baul
    */
    @Test
    public void itemTurnTest() throws IOException, InvalidSelectionException, InvalidAttackException {



        // Battle Begins
        controller.createAndSetNewBattle();

        // The player's vault should be created, and 3 items of each kind should be added to it.
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));


        controller.selectNewTurnKind(TurnType.ITEM);

//        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
        assertEquals(TurnType.ITEM, controller.getCurrentTurn().getType());
        assertEquals(TurnOwner.ENEMY,controller.calculateNextTurnOwner());


        /*
        Se usa un item con marco (1)
        Se usa el item "Honey Syrup" (1)
         */
        BufferedReader reader = new BufferedReader(new StringReader("1\n1"));
        controller.getCurrentTurn().setReader(reader);


        controller.startCurrentTurn();

        // Check whether marco is receiving the item.
        assertEquals(EntityType.MARCO,controller.getCurrentTurn().getInvolvedMainCharacter().getType());

        // Let's check if 1 honey was used
        assertEquals(2, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        controller.finishTurn();

    }



    @Test
    public void passingTest() throws IOException, InvalidSelectionException, InvalidAttackException {

        controller.createAndSetNewBattle();

        controller.selectNewTurnKind(TurnType.PASSING);

//        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
        assertEquals(TurnType.PASSING, controller.getCurrentTurn().getType());
        assertEquals(TurnOwner.ENEMY,controller.calculateNextTurnOwner());

        // Should do nothing.
        controller.startCurrentTurn();

        InterMainCharacter playerMarco = controller.getPlayer().getMarco();

        // Como este es un turno de paso, no hay personaje de turno.
        assertNull(controller.getCurrentTurn().getInvolvedMainCharacter());

        // Nada debería haber cambiado con los jugadores de base.
        assertNotNull(playerMarco);
        assertEquals(10, playerMarco.getAtk());
        assertEquals(10, playerMarco.getDef());
        assertEquals(20, playerMarco.getHp());
        assertEquals(20, playerMarco.getMaxHP());
        assertEquals(20, playerMarco.getFp());
        assertEquals(20, playerMarco.getMaxFP());
        assertEquals(1, playerMarco.getLvl());

        controller.finishTurn();

        // Asegurarnos que nada haya pasado, y que el siguiente turno sea del enemigo.
        assertEquals(TurnType.ENEMY,controller.getCurrentTurn().getType());
    }



}

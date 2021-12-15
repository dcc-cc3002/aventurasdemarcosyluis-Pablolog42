package TestController.featureTest;//package TestController.featureTest;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.backend.controller.turns.ItemTurn;
import aventurasdemarcoyluis.backend.controller.turns.TurnOwner;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.Goomba;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TurnTest {


    GameController controller;
    InterMainCharacter exampleMainCharacter;
    InterEnemy exampleGoomba;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setPlayer("Ernesto Sábato");
    }

    @Test
    public void constructorTest() {
        assertEquals(TurnOwner.MARCO, controller.getCurrentTurnOwner());
        assertNull(controller.getCurrentTurn());

    }

    @Test
    public void creationTest() {
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
        exampleMainCharacter = controller.createMainCharacter(EntityType.MARCO, 10, 11, 12, 100, 10, 100, 1);

        assertTrue(exampleMainCharacter instanceof Marco);
        assertEquals(10, exampleMainCharacter.getAtk());
        assertEquals(11, exampleMainCharacter.getDef());
        assertEquals(12, exampleMainCharacter.getHp());
        assertEquals(100, exampleMainCharacter.getMaxHP());
        assertEquals(10, exampleMainCharacter.getFp());
        assertEquals(100, exampleMainCharacter.getMaxFP());

        exampleGoomba = controller.createEnemy(EntityType.GOOMBA, 10, 10, 10, 10, 1);

        assertTrue(exampleGoomba instanceof Goomba);
        assertEquals(10, exampleGoomba.getAtk());
        assertEquals(10, exampleGoomba.getDef());
        assertEquals(10, exampleGoomba.getHp());
        assertEquals(10, exampleGoomba.getMaxHP());

    }


    /* Assert that we can achieve the requirements:
         5. Create a turn (Attack turn)
         5. Implement the turn logic (player -> enemy -> player...)
         11. Get a turn's current main character (the one attacking or using an item)
         12. Get the next turn's main characters and enemies
         13. Finish the current turn
    */
    @Test
    public void attackTurnTest() throws InvalidSelectionException, InvalidTransitionException, InvalidAttackException {

        controller.runFirstBattle();

        // The player's vault should be created, and 3 items of each kind should be added to it.
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));


        controller.toNextPhase(new WaitSelectTurnTypePhase(controller));


        controller.getCurrentPhase().selectTurnKind(TurnType.ATTACK);

        controller.getCurrentPhase().selectAttackType(AttackType.JUMP);



        /* Assert that we can achieve the requirements:
             5. Implementar los turnos - parte de la implementation que define el turno actual.
             11. Obtener el personaje del turno actual. - getCurrentTurnOwner()
             12. Obtener Personaje del siguiente Turno (en este caso, se obtiene si es el turno del jugador, o del enemigo) - getNextTurnOwner()
        */
        assertEquals(TurnOwner.MARCO, controller.getCurrentTurnOwner());
        assertEquals(TurnType.ATTACK, controller.getCurrentTurn().getType());
        assertEquals(TurnOwner.LUIS, controller.getNextTurnOwner());



    }



    /* Assert that we can achieve the requirements:
      4. crear el baul de personajes principales
      5. Create a turn (Item turn)
      6. Que un jugador pueda usar un elemento del baul
      7. Obtener los elementos del baul
    */
    @Test
    public void itemTurnTest() throws InvalidSelectionException, InvalidTransitionException, InvalidAttackException {



        // Battle Begins
        controller.createAndSetNewBattle();

        // The player's vault should be created, and 3 items of each kind should be added to it.
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));


        controller.tryToSelectNewTurnKind(TurnType.ITEM);
        ItemTurn itemTurn = new ItemTurn(controller);
        itemTurn.setSelectedItem(ItemType.HONEYSYRUP);


        itemTurn.main();

        assertEquals(TurnType.ITEM, controller.getCurrentTurn().getType());
        assertEquals(TurnOwner.LUIS,controller.getNextTurnOwner());



        /*
        Se usa un item con marco (1)
        Se usa el item "Honey Syrup" (1)
         */

        // Let's check if 1 honey was used
        assertEquals(2, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
        controller.finishTurn();

    }


}

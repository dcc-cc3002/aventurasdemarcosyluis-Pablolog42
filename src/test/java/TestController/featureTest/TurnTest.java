package TestController.featureTest;//package TestController.featureTest;
//
//import aventurasdemarcoyluis.controller.GameController;
//import aventurasdemarcoyluis.controller.turns.TurnOwner;
//import aventurasdemarcoyluis.controller.turns.TurnType;
//import aventurasdemarcoyluis.model.EntityType;
//import aventurasdemarcoyluis.model.enemies.Goomba;
//import aventurasdemarcoyluis.model.enemies.InterEnemy;
//import aventurasdemarcoyluis.model.items.ItemType;
//import aventurasdemarcoyluis.model.maincharacters.AbstractMainCharacter;
//import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
//import aventurasdemarcoyluis.model.maincharacters.Luis;
//import aventurasdemarcoyluis.model.maincharacters.Marco;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.StringReader;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TurnTest {
//
//
//    GameController controller;
//    InterMainCharacter exampleMainCharacter;
//    InterEnemy exampleGoomba;
//
//    @BeforeEach
//    public void setUp() {
//        controller = new GameController();
//        controller.setPlayer("Ernesto Sábato");
//    }
//
//    @Test
//    public void constructorTest(){
//        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
//        assertNull(controller.getCurrentTurn());
//
//
//
//    }
//
//    @Test
//    public void creationTest(){
//        /* Assert that we can achieve the requirements:
//             1. Create main characters.
//             2. Create Enemies
//             3. Create Items
//             4. Create Item Vault
//        */
//
//        // Assert the constructor can create main characters
//        assertTrue(controller.getPlayerMainCharacter(EntityType.MARCO) instanceof Marco);
//        assertTrue(controller.getPlayerMainCharacter(EntityType.LUIS) instanceof Luis);
//
//        // Assert that controller.createMainCharacter() can create main characters.
//        exampleMainCharacter = controller.createMainCharacter(EntityType.MARCO,10,11,12,100,10,100,1);
//
//        assertTrue(exampleMainCharacter instanceof Marco);
//        assertEquals(10, exampleMainCharacter.getAtk());
//        assertEquals(11, exampleMainCharacter.getDef());
//        assertEquals(12, exampleMainCharacter.getHp());
//        assertEquals(100, exampleMainCharacter.getMaxHP());
//        assertEquals(10, exampleMainCharacter.getFp());
//        assertEquals(100, exampleMainCharacter.getMaxFP());
//
//        exampleGoomba = controller.createEnemy(EntityType.GOOMBA, 10,10,10,10,1);
//
//        assertTrue(exampleGoomba instanceof Goomba);
//        assertEquals(10, exampleGoomba.getAtk());
//        assertEquals(10, exampleGoomba.getDef());
//        assertEquals(10, exampleGoomba.getHp());
//        assertEquals(10, exampleGoomba.getMaxHP());
//
//    }
//
//
//    /* Assert that we can achieve the requirements:
//         5. Create a turn (Attack turn)
//         5. Implement the turn logic (player -> enemy -> player...)
//         11. Get a turn's current main character (the one attacking or using an item)
//         12. Get the next turn's main characters and enemies
//         13. Finish the current turn
//    */
//    @Test
//    public void attackTurnTest() throws IOException {
//
//
//
//        // Battle Begins
//        controller.createAndSetNewBattle();
//
//        // The player's vault should be created, and 3 items of each kind should be added to it.
//        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
//        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));
//
//
//        controller.selectTurnKind("attack");
//        /* Assert that we can achieve the requirements:
//             5. Implementar los turnos - parte de la implementation que define el turno actual.
//             11. Obtener el personaje del turno actual. - getCurrentTurnOwner()
//             12. Obtener Personaje del siguiente Turno (en este caso, se obtiene si es el turno del jugador, o del enemigo) - getNextTurnOwner()
//        */
//        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
//        assertEquals(TurnType.ATTACK, controller.getCurrentTurn().getType());
//        assertEquals(TurnOwner.ENEMY,controller.getNextTurnOwner());
//
//
//        /*
//        Se Ataca al primer enemigo aleatorio que aparezaca. (1)
//        Se ataca con marco (1)
//        Se hace un jump-attack (1)
//         */
//        BufferedReader reader = new BufferedReader(new StringReader("1\n1\n1"));
//        controller.getCurrentTurn().setReader(reader);
//
//        // The attack turn is started, and the partameters given in the buffer reader are selected.
//        controller.startCurrentTurn();
//
//        // test that we can get the current turn's main character
//        assertTrue(controller.getCurrentTurn().getInvolvedMainCharacter() instanceof Marco);
//
//        // the turn is finished, and enemies can now attack.
//        controller.finishTurn();
//
//        // as the turn has finished, this should be an enemy turn
//        assertEquals(TurnType.ENEMY, controller.getCurrentTurn().getType());
//        // Thus, the next turn should be the player's turn.
//        assertEquals(TurnOwner.PLAYER, controller.getNextTurnOwner());
//
//        // The enemy turn is started.
//        controller.startCurrentTurn();
//
//        // as said, we attack with marco
//        assertTrue(controller.getCurrentTurn().getInvolvedMainCharacter() instanceof AbstractMainCharacter);
//
//        // The turn is now finished
//        controller.finishTurn();
//
//        // lets check if the turn logic works after 2 turns
//        assertEquals(TurnOwner.PLAYER, controller.getCurrentTurnOwner());
//
//
//
//    }
//
//    /* Assert that we can achieve the requirements:
//      4. crear el baul de personajes principales
//      5. Create a turn (Item turn)
//      6. Que un jugador pueda usar un elemento del baul
//      7. Obtener los elementos del baul
//    */
//    @Test
//    public void itemTurnTest() throws IOException {
//
//
//
//        // Battle Begins
//        controller.createAndSetNewBattle();
//
//        // The player's vault should be created, and 3 items of each kind should be added to it.
//        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
//        assertEquals(3, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.REDMUSHROOM));
//
//
//        controller.selectTurnKind("item");
//
//        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
//        assertEquals(TurnType.ITEM, controller.getCurrentTurn().getType());
//        assertEquals(TurnOwner.ENEMY,controller.getNextTurnOwner());
//
//
//        /*
//        Se usa un item con marco (1)
//        Se usa el item "Honey Syrup" (1)
//         */
//        BufferedReader reader = new BufferedReader(new StringReader("1\n1"));
//        controller.getCurrentTurn().setReader(reader);
//
//
//        controller.startCurrentTurn();
//
//        // Check whether marco is receiving the item.
//        assertEquals(EntityType.MARCO,controller.getCurrentTurn().getInvolvedMainCharacter().getType());
//
//        // Let's check if 1 honey was used
//        assertEquals(2, controller.getPlayer().getPlayerVault().getItemAmount(ItemType.HONEYSYRUP));
//        controller.finishTurn();
//
//    }
//
//
//
//    @Test
//    public void passingTest() throws IOException {
//
//        controller.createAndSetNewBattle();
//
//        controller.selectTurnKind("passing");
//
//        assertEquals(TurnOwner.PLAYER,controller.getCurrentTurnOwner());
//        assertEquals(TurnType.PASSING, controller.getCurrentTurn().getType());
//        assertEquals(TurnOwner.ENEMY,controller.getNextTurnOwner());
//
//        // Should do nothing.
//        controller.startCurrentTurn();
//
//        InterMainCharacter playerMarco = controller.getPlayer().getMarco();
//
//        // Como este es un turno de paso, no hay personaje de turno.
//        assertNull(controller.getCurrentTurn().getInvolvedMainCharacter());
//
//        // Nada debería haber cambiado con los jugadores de base.
//        assertNotNull(playerMarco);
//        assertEquals(10, playerMarco.getAtk());
//        assertEquals(10, playerMarco.getDef());
//        assertEquals(20, playerMarco.getHp());
//        assertEquals(20, playerMarco.getMaxHP());
//        assertEquals(20, playerMarco.getFp());
//        assertEquals(20, playerMarco.getMaxFP());
//        assertEquals(1, playerMarco.getLvl());
//
//        controller.finishTurn();
//
//        // Asegurarnos que nada haya pasado, y que el siguiente turno sea del enemigo.
//        assertEquals(TurnType.ENEMY,controller.getCurrentTurn().getType());
//    }
//
//
//
//}

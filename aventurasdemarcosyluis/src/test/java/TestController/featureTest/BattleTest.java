//package TestController.featureTest;
//
//import aventurasdemarcoyluis.controller.GameController;
//import aventurasdemarcoyluis.controller.Player;
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
//public class BattleTest {
//
//
//    GameController controller;
//
//    @BeforeEach
//    public void setUp() {
//        controller = new GameController();
//        controller.setPlayer("Ernesto Sábato");
//    }
//
//    /*
//    Asserts that the game is lost when the player characters are both KO.
//
//    Tests the following requisites:
//
//    9. quitar un personaje del turno cuando está KO
//    10. Saber cuando los personajes principales pierden
//     */
//    @Test
//    public void gameLostTest() throws IOException {
//        // Battle Begins
//        controller.createAndSetNewBattle();
//
//        // let's pass until all enemies are KO
//        while(!controller.isGameFinished()) {
//            // Player passes and finishes turn
//            controller.selectTurnKind("passing");
//            controller.startCurrentTurn();
//            controller.finishTurn();
//            // enemy turn
//            controller.startCurrentTurn();
//            controller.finishTurn();
//        }
//
//        assertTrue(controller.getPlayer().isPlayerKO());
//
//        assertTrue(controller.isGameFinished());
//        // winner false -> assert the player has lost.
//        assertFalse(controller.getWinner());
//
//    }
//
//    /*
//    Asserts that the game is won when the player wins 5 concurrent battles.
//
//    Tests the following requisites:
//
//    10. Saber cuando los personajes principales pierden
//     */
//    @Test
//    public void gameWon5BattlesTest() throws IOException {
//        // Battle Begins
//        controller.createAndSetNewBattle();
//        // Generamos 5 batallas que asumiremos como ganadas (donde ni enemigos ni jugador pierden.)
//       for(int i = 0; i<5; i++) {
//           controller.selectTurnKind("passing");
//           controller.startCurrentTurn();
//           controller.finishTurn();
//           controller.createAndSetNewBattle();
//       }
//
//        // Luego de 5 batallas ganadas,
//        assertFalse(controller.getPlayer().isPlayerKO());
//
//        assertTrue(controller.isGameFinished());
//        // winner false -> assert the player has lost.
//        assertTrue(controller.getWinner());
//
//    }
//
//
//    /*
//    Asserts that the battle is won when the player kills all players.
//
//    Tests the following requisites:
//
//    10. Saber cuando los personajes principales pierden
// */
//    @Test
//    public void battleWonTest() throws IOException {
//
//        // These players should kill all enemies pretty quickly
//        Marco killerMarco = new Marco(10000,10000,10000,10000,1000,1000,1);
//        Luis killerLuis = new Luis(10000,10000,10000,10000,1000,1000,1);
//
//        Player killerPlayer = new Player("Claudio Falcón",killerMarco,killerLuis);
//
//        GameController controller1 = new GameController();
//        controller1.setPlayer(killerPlayer);
//
//        controller1.createAndSetNewBattle();
//        controller1.selectTurnKind("attack");
//
//
//        BufferedReader reader = new BufferedReader(new StringReader("1\n1\n1\n2\n1\n1\n3\n1\n1"));
//        controller1.getCurrentTurn().setReader(reader);
//        controller1.startCurrentTurn();
//        controller1.finishTurn();
//
//        // Enemy turn
//        controller1.startCurrentTurn();
//        controller1.finishTurn();
//
//        controller1.selectTurnKind("attack");
//        controller1.getCurrentTurn().setReader(reader);
//        controller1.startCurrentTurn();
//        controller1.finishTurn();
//
//        // Enemy turn
//        controller1.startCurrentTurn();
//        controller1.finishTurn();
//
//        controller1.selectTurnKind("attack");
//        controller1.getCurrentTurn().setReader(reader);
//        controller1.startCurrentTurn();
//        controller1.finishTurn();
//
//
//
//        // Enemy turn
//        controller1.startCurrentTurn();
//        controller1.finishTurn();
//
//
//        // Every enemy should be dead.
//        assertTrue(controller.getPlayer().getEnemyList().isListKO());
//
//    }
//
//
//
//}

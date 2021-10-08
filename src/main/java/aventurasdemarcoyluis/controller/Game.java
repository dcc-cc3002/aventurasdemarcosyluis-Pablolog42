package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.Player;
import aventurasdemarcoyluis.controller.turns.EnemyTurn;

public class Game {



    public static void main(String[] args) {
        Player pablo = new Player("Pablo");
        EnemyTurn enemyTurn = new EnemyTurn(pablo);
        enemyTurn.main();
    }
}

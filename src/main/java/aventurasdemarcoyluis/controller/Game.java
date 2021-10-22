package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.Battle;
import aventurasdemarcoyluis.Player;
import aventurasdemarcoyluis.controller.turns.EnemyTurn;
import aventurasdemarcoyluis.entities.items.ItemType;

public class Game {

    private static int battleNumber = 0;
    private static boolean gameFinished = false;

    public static void main(String[] args) {
        Player pablo = new Player("Pablo");

        Battle batalla = new Battle();


        battleNumber = battleNumber + 1;
        batalla.main(pablo);

    }
}

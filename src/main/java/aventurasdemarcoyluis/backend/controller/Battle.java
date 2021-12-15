package aventurasdemarcoyluis.backend.controller;

import aventurasdemarcoyluis.backend.model.Player;
import aventurasdemarcoyluis.backend.model.items.ItemType;

/**
 * Class that represents a whole battle in the game.
 */
public class Battle {

    protected final Player player;
    private GameController controller;

    /**
     * Battle Class constructor.
     * Creates a new controller
     *
     * @param player
     */
    public Battle(Player player, GameController controller) {
        this.player = player;
        this.controller = controller;
    }

    /**
     * Adds a number of random characters to the player's Enemy List.
     * The number of enemies added depends on the number of battles already played, as indicated
     * by the getBattleNumber() method.
     * <p>
     * in case getBattleNumber() equals 0,1 -> clears previous list and adds 3 random enemies
     * in case getBattleNumber() equals 2,3 -> clears previous list and adds 5 random enemies
     * in case getBattleNumber() equals 4 -> clears previous list and adds 6 random enemies
     **/
    public void setRandomEnemyList() {
        // Seleccionar n tipos random, acorde al numero de batalla que se está jugando
        int battleNumber = this.player.getBattleNumber();
        switch (battleNumber) {
            case 0, 1 -> {
                this.controller.getEnemyList().clearList();
                this.controller.getEnemyList().addRandomEnemies(3);
            }
            case 2, 3 -> {
                this.controller.getEnemyList().clearList();
                this.controller.getEnemyList().addRandomEnemies(5);
            }
            case 4 -> {
                this.controller.getEnemyList().clearList();
                this.controller.getEnemyList().addRandomEnemies(6);
            }
        }
    }

}


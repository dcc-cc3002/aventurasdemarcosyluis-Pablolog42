package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.items.ItemType;

/**
 * Class that represents a whole battle in the game.
 */
public class Battle {

    private boolean battleFinished;
    protected Player player;
    private String battleWinner;

    /**
     * Battle Class constructor.
     * Creates a new controller
     * @param player
     */
    public Battle(Player player) {
        this.player = player;
        this.battleFinished = false;
    }

    /**
     * Adds a number of random characters to the player's Enemy List.
     * The number of enemies added depends on the number of battles already played, as indicated
     * by the getBattleNumber() method.
     *
     * in case getBattleNumber() equals 0,1 -> clears previous list and adds 3 random enemies
     * in case getBattleNumber() equals 2,3 -> clears previous list and adds 5 random enemies
     * in case getBattleNumber() equals 4 -> clears previous list and adds 6 random enemies
     **/
    public void setRandomEnemyList() {
        // Seleccionar n tipos random, acorde al numero de batalla que se está jugando
        int battleNumber = this.player.getBattleNumber();
        switch (battleNumber) {
            case 0, 1 -> {
//                System.out.println("deberian de haber 3 enemigos aleatorios");
                this.player.getEnemyList().clearList();
                this.player.getEnemyList().addRandomEnemies(3);
            }
            case 2, 3 -> {
//                System.out.println("deberian de haber 5 enemigos aleatorios");
                this.player.getEnemyList().clearList();
                this.player.getEnemyList().addRandomEnemies(5);
            }
            case 4 -> {
//                System.out.println("deberian de haber 6 enemigos aleatorios");
                this.player.getEnemyList().clearList();
                this.player.getEnemyList().addRandomEnemies(6);
            }
        }
    }

    /**
     * Adds the items specified for the first battle ( * in case getBattleNumber() equals 0)
     * Specifically, dds 3 Honey and 3 Mushroom.
     */
    public void addInitialItems() {
        // En la primera batalla, se agregan 3 items de c/u al inventario del jugador.
        this.player.addAnItem(ItemType.HONEYSYRUP, 3);
        this.player.addAnItem(ItemType.REDMUSHROOM, 3);

        // Deprecated: esto ahora se hace solo al hacer player.lvlUp()
//            case 1, 2, 3, 4, 5 -> {
//                // en las batallas posteriores, se agrega 1 item de c/u
//                this.player.addAnItem(ItemType.HONEYSYRUP);
//                this.player.addAnItem(ItemType.REDMUSHROOM);
//            }


    }

}


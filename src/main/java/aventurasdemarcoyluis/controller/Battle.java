package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.items.ItemType;

public class Battle {

    private boolean battleFinished;
    protected Player player;
    protected GameController controller;
    private String battleWinner;


    public Battle(Player player) {
        this.controller = new GameController();
        this.player = player;
        this.battleFinished = false;
    }


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


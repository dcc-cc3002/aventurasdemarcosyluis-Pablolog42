package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.turns.AttackTurn;
import aventurasdemarcoyluis.controller.turns.EnemyTurn;
import aventurasdemarcoyluis.controller.turns.ItemTurn;
import aventurasdemarcoyluis.controller.turns.PassingTurn;
import aventurasdemarcoyluis.model.items.ItemType;

import java.util.Scanner;

public class Battle {

    private boolean battleFinished;
    protected Player player;
    protected GameController controller;
    private String battleWinner;




    public Battle(Player player){
        this.controller = new GameController();
        this.player = player;
        this.battleFinished = false;
    }


    public void main() {
        // Se agregan los items Correspondientes al inventario del jugador.
        this.addInitialItems();


        boolean currentPlayFinished = false;
        Scanner entrada = new Scanner(System.in);

        while(!this.battleFinished) {

            // Se genera la lista de enemigos aleatorios correspondiente segun el numero de batalla en el que se
            // encuentre el jugador. Esta lista de enemigos es la misma a lo largo de un turno entero, y cambia
            // solo al iniciarse una nueva batalla
            this.setRandomEnemyList();

            while (!currentPlayFinished) {



                if(this.checkKoRoutine()){
                    this.battleFinished = true;
                    this.controller.setGameFinished(true);
                    return;
                }

                System.out.println();

                System.out.println("*********************************************");
                System.out.println(this.player.getPlayerName()+ "'s main characters are: ");
                System.out.println(this.player.getMarco() + "\n" + this.player.getLuis());

                System.out.println("*********************************************");
                System.out.println("Your team encounters a series of enemies to battle!");
                System.out.println("Wild enemies:");
                System.out.println(this.player.getEnemyList());
                System.out.println("*********************************************");




                System.out.println();
                System.out.println("--------- Turn Begins ---------");
                System.out.println("It's your turn. Please choose what do you want to do: (Pick a number from below)");
                System.out.println("1. Attack    2. Use Item    3. Pass");

                String selection = entrada.nextLine();


                switch (selection) {
                    case "1" -> {
                        AttackTurn attackTurn = new AttackTurn(player);
                        attackTurn.main();

                    }
                    case "2" -> {
                        // In case the player wants to use an item, but has none left.
                        if (player.getPlayerVault().isEmpty()){
                            System.out.println("You can't use an item, as you have none left!");
                            continue;
                        }

                        ItemTurn itemTurn = new ItemTurn(player);
                        itemTurn.main();
                    }
                    case "3" -> {
                        PassingTurn passingTurn = new PassingTurn(player);
                        passingTurn.main();
                    }
                    default -> {
                        System.out.println("Please, select a valid option");
                        continue;
                    }
                }




                // En caso de que el jugador no esté KO luego
                EnemyTurn enemyTurn = new EnemyTurn(this.player);
                enemyTurn.main();

                //TODO: en este punto, hay que chequear si el jugador, o todos los enemigos estan ko para ver si se termina la batalla

                if(this.checkKoRoutine()){
                    this.battleFinished = true;
                    this.controller.setGameFinished(true);
                    return;
                }


                // en caso de no estar KO, ha terminado el juego
                System.out.println("--------- End of Turn ---------");

            }
            this.battleFinished = true;
            // Al finalizar la batalla, se agrega 1 al contador de batallas.
            if (this.player.getBattleNumber() >= 5 || this.battleFinished) {
                this.battleFinished = true;
                this.battleWinner = "Player";
                System.out.println();
                System.out.println(" || Fin de la batalla numero " + (this.player.getBattleNumber()+1) + " ||");
                System.out.println(" || Ganador de batalla: " + this.player.getPlayerName() + " ||");
            }
        }

    }


    private boolean checkKoRoutine() {

        if(this.player.isPlayerKO()){
            System.out.println("All of " + this.player.getPlayerName() + "'s characters are KO. \nThe Game is now over!");
            System.out.println("The enemy has won :(");
            return true;
        }
        return false;
    }


    public void setRandomEnemyList(){
        // Seleccionar n tipos random, acorde al numero de batalla que se está jugando
        int battleNumber = this.player.getBattleNumber();
        switch (battleNumber){
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



    public void addInitialItems(){
        int battleNumber = this.player.getBattleNumber();
        switch (battleNumber){
            case 0 -> {
                // En la primera batalla, se agregan 3 items de c/u al inventario del jugador.
                this.player.addAnItem(ItemType.HONEYSYRUP,3);
                this.player.addAnItem(ItemType.REDMUSHROOM,3);
            }
            case 1, 2, 3, 4, 5 -> {
                // en las batallas posteriores, se agrega 1 item de c/u
                this.player.addAnItem(ItemType.HONEYSYRUP);
                this.player.addAnItem(ItemType.REDMUSHROOM);
            }

        }
    }



    public String returnWinner(){
        return battleWinner;
    }


}

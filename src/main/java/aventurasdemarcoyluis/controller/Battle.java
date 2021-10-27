package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.turns.AttackTurn;
import aventurasdemarcoyluis.controller.turns.EnemyTurn;
import aventurasdemarcoyluis.controller.turns.ItemTurn;
import aventurasdemarcoyluis.controller.turns.PassingTurn;
import aventurasdemarcoyluis.entities.enemies.Boo;
import aventurasdemarcoyluis.entities.enemies.InterEnemy;

import java.util.List;
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

        boolean currentTurnFinished = false;

        while(!this.battleFinished) {

            currentTurnFinished = false;

            // Se genera la lista de enemigos aleatorios correspondiente segun el numero de batalla en el que se
            // encuentre el jugador. Esta lista de enemigos es la misma a lo largo de un turno entero, y cambia
            // solo al iniciarse una nueva batalla
            setRandomEnemyList();

            while (!currentTurnFinished) {

                Scanner entrada = new Scanner(System.in);

                System.out.println("----- Inicio de Turno -----");
                System.out.println("Es su turno. Indique a continuación que desea realizar. (Ingrese un número)");
                System.out.println("1. Atacar    2. Usar Item    3. Pasar");

                String selection = entrada.nextLine();

                switch (selection) {
                    case "1" -> {
                        AttackTurn attackTurn = new AttackTurn(player);
                        attackTurn.main(this.player);
                    }
                    case "2" -> {
                        ItemTurn itemTurn = new ItemTurn(player);
                        itemTurn.main(this.player);
                    }
                    case "3" -> {
                        PassingTurn passingTurn = new PassingTurn(player);
                        passingTurn.main(this.player);
                    }
                    //TODO: sacar esto, es solo para debuging.
                    case "stop" -> this.battleFinished = true;
                }


                // En caso de que el jugador no esté KO luego
                EnemyTurn enemyTurn = new EnemyTurn(this.player);
                enemyTurn.main();

                //TODO: en este punto, hay que chequear si el jugador, o todos los enemigos estan ko para ver si se termina la batalla


                // en caso de no estar KO, ha terminado el turno
                System.out.println(" --------- Fin del turno ---------");
                currentTurnFinished = true;

            }
            //this.battleFinished = true;
            // Al finalizar la batalla, se agrega 1 al contador de batallas.
            if (this.player.getBattleNumber() >= 5 || this.battleFinished) {
                this.battleFinished = true;
                this.battleWinner = "Player";
                System.out.println(" || --------- Fin de la batalla numero " + (this.player.getBattleNumber()+1) + " ----------- ||");
                System.out.println(" || Ganador de batalla: " + this.player.getPlayerName() + " ||");
            }
        }

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

    public String returnWinner(){
        return battleWinner;
    }


}

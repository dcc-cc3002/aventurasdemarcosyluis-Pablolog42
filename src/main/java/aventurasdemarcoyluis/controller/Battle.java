package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.turns.AttackTurn;
import aventurasdemarcoyluis.controller.turns.EnemyTurn;
import aventurasdemarcoyluis.controller.turns.ItemTurn;
import aventurasdemarcoyluis.controller.turns.PassingTurn;

import java.util.Scanner;

public class Battle {

    private Boolean battleFinished;
    private int battleNumber;
    protected GameController controller;


    public Battle(){
        this.controller = new GameController();
        this.battleFinished = false;
    }


    public void main(Player player) {

        while(!this.battleFinished) {


            Scanner entrada = new Scanner(System.in);

            System.out.println("Se inicia la batalla");
            System.out.println("Es su turno. Indique a continuación que desea realizar. (Ingrese un número)");
            System.out.println("1. Atacar    2. Usar Item    3. Pasar");

            String selection = entrada.nextLine();

            switch (selection){
                case "1" -> {AttackTurn attackTurn = new AttackTurn(player); attackTurn.main(player);}
                case "2" -> {ItemTurn itemTurn = new ItemTurn(player); itemTurn.main(player);}
                case "3" -> {PassingTurn passingTurn = new PassingTurn(player); passingTurn.main(player);}
                case "stop" -> {this.battleFinished = true;}
            }


            // En caso de que el jugador no esté KO luego
            EnemyTurn enemyTurn = new EnemyTurn(player);
            enemyTurn.main(player);

            //TODO: en este punto, hay que chequear si el jugador esta ko para ver si se termina la batalla





            //this.battleFinished = true;
            // Al finalizar la batalla, se agrega 1 al contador de batallas.
            player.increaseBattleNumber();

        }



    }


    protected void playerVictorySequence() {
        System.out.println("The Player has won. Congratulations!");
    }

    protected void enemyVictorySequence() { System.out.println("All players characters have been defeated. Evil will now reign."); }

}

package aventurasdemarcoyluis.view;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.Player;

import java.util.Scanner;

public class TextUI implements InterUI {

    GameController controller;

    public TextUI(){
        this.controller = new GameController();
    }

    @Override
    public void main() {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Please, enter your name: ");
        String playerName = entrada.nextLine();

        controller.setPlayer(playerName);

        for(int i=0; i < 5; i++ ) {
            // https://pbs.twimg.com/media/EvHKoVAWYAQpUvJ.jpg
            if(!controller.isGameFinished()) controller.runBattle();
        }



    }


    public void playerVictorySequence() {
        System.out.println("The Player has won. Congratulations!");
    }


    public void enemyVictorySequence() { System.out.println("All players characters have been defeated. Evil will now reign."); }


}

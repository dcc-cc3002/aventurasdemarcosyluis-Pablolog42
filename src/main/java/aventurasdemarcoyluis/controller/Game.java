package aventurasdemarcoyluis.controller;

public class Game {

    public static void main(String[] args) {
        Player pablo = new Player("Pablo");


        Battle batalla1 = new Battle(pablo);
        Battle batalla2 = new Battle(pablo);
        Battle batalla3 = new Battle(pablo);
        Battle batalla4 = new Battle(pablo);
        Battle batalla5 = new Battle(pablo);


        batalla1.main();
        pablo.increaseBattleNumber();
        batalla2.main();
        pablo.increaseBattleNumber();
        batalla3.main();
        pablo.increaseBattleNumber();
        batalla4.main();
        pablo.increaseBattleNumber();
        batalla5.main();

        if(batalla1.returnWinner().equals("Player")){
            playerVictorySequence();
        }

    }

    public static void playerVictorySequence() {
        System.out.println("The Player has won. Congratulations!");
    }

    public static void enemyVictorySequence() { System.out.println("All players characters have been defeated. Evil will now reign."); }

}

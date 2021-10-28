package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.enemies.*;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.maincharacters.Luis;
import aventurasdemarcoyluis.entities.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class GameController {

    public GameController(){

    }

    public void main() {

        Scanner entrada = new Scanner(System.in);
        System.out.print("Please, enter your name: ");
        String playerName = entrada.nextLine();


        Player mainPlayer = new Player(playerName);

        // https://pbs.twimg.com/media/EvHKoVAWYAQpUvJ.jpg
        Battle batalla1 = new Battle(mainPlayer);
        Battle batalla2 = new Battle(mainPlayer);
        Battle batalla3 = new Battle(mainPlayer);
        Battle batalla4 = new Battle(mainPlayer);
        Battle batalla5 = new Battle(mainPlayer);


        batalla1.main();
        mainPlayer.increaseBattleNumber();
        batalla2.main();
        mainPlayer.increaseBattleNumber();
        batalla3.main();
        mainPlayer.increaseBattleNumber();
        batalla4.main();
        mainPlayer.increaseBattleNumber();
        batalla5.main();

        if(batalla1.returnWinner().equals("Player")){
            playerVictorySequence();
        }

    }

    public static void playerVictorySequence() {
        System.out.println("The Player has won. Congratulations!");
    }

    public static void enemyVictorySequence() { System.out.println("All players characters have been defeated. Evil will now reign."); }



    public AbstractMainCharacter createMainCharacter(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int fp, int maxFP, int lvl){
        switch (type){
            case MARCO -> { return new Marco(atk,def,fp,maxFP,hp,maxHP,lvl); }
            case LUIS -> { return new Luis(atk,def,fp,maxFP,hp,maxHP,lvl);}
        }
        return null;
    }

    public AbstractEnemy createEnemy(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int lvl){
        switch (type){
            case GOOMBA -> { return new Goomba(atk,def,hp,maxHP,lvl); }
            case BOO -> { return new Boo(atk,def,hp,maxHP,lvl); }
            case SPINY -> { return new Spiny(atk,def,hp,maxHP,lvl); }
        }
        return null;
    }

    // To create an enemy with random stats.
    // The range limits for the stats are defined as follow:

    public AbstractEnemy createRandomStatsEnemy(@NotNull EnemyType type){

        // Generates an array with 4 random stat values
        int[] stats = new int[4];
        for (int i = 0; i <= 3; i++) { stats[i] = ThreadLocalRandom.current().nextInt(1, 15 + 1); }

        switch (type){
            // Note: MAXHP has to be grater than hp, and hp has to be grater than 1
            case GOOMBA -> { return new Goomba(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
            case BOO -> { return new Boo(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
            case SPINY -> { return new Spiny(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
        }
        return null;
    }










}

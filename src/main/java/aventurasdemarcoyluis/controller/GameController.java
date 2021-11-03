package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.*;
import aventurasdemarcoyluis.model.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class GameController {

    private String currentBattleWinner;
    private Battle currentBattle;
    private Player mainPlayer;
    private Boolean winner;

    public GameController(){
        this.currentBattleWinner = null;
        this.currentBattle = null;
        this.winner = null;
    }




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

    public void checkForWinner(){
        //TODO:
        if(this.mainPlayer.getBattleNumber() >= 5){

        }
    }

    public void runBattle(){
        currentBattle = new Battle(this.mainPlayer);
        currentBattle.main();
        mainPlayer.increaseBattleNumber();
        this.checkForWinner();
    }


    public void setPlayer(String playerName) {
        this.mainPlayer = new Player(playerName);
    }


    // Getters and setters

    public String getCurrentBattleWinner() {
        return currentBattleWinner;
    }

    public Battle getCurrentBattle() {
        return currentBattle;
    }
}

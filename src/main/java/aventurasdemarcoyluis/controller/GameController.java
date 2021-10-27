package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.enemies.*;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.maincharacters.Luis;
import aventurasdemarcoyluis.entities.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.ThreadLocalRandom;


public class GameController {

    public GameController(){

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
        for (int i = 0; i <= 3; i++) { stats[i] = ThreadLocalRandom.current().nextInt(0, 20 + 1); }

        switch (type){
            // Note: MAXHP has to be grater than hp, and hp has to be grater than 1
            case GOOMBA -> { return new Goomba(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
            case BOO -> { return new Boo(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
            case SPINY -> { return new Spiny(stats[0],stats[1],stats[2]+1,stats[2]+2,stats[3]); }
        }
        return null;
    }










}

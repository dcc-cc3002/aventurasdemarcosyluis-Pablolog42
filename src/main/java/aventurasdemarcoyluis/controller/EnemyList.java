package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.enemies.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyList {


    ArrayList<InterEnemy> list;

    public EnemyList(){
        this.list = new ArrayList<>();
    }

    public void addRandomEnemies(int enemiesToAdd){
        for(int i=0; i<enemiesToAdd; i++){
            list.add(createRandomStatsEnemy(EnemyType.getRandomEnemyType()));
        }
    }

    public InterEnemy retrieveRandomEnemy(){
        Random rand = new Random();
        return this.list.get(rand.nextInt(this.list.size()));
    }

    public InterEnemy retrieveEnemy(int i){
        return this.list.get(i);
    }




    public void clearList(){
        this.list.clear();
    }

    @Override
    public String toString(){
        String out = "";
        for(InterEnemy enemy : this.list){
            out = out.concat(enemy.toString()+ "\n");
        }
        if(out.equals("")) return "No enemies in enemy list";
        return out;
    }

    // To create an enemy with random stats.
    // The range limits for the stats are defined as follows:

    public InterEnemy createRandomStatsEnemy(@NotNull EnemyType type){

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

    public boolean isListKO(){
        ArrayList<Boolean> listKOStatus = new ArrayList<>();
        for(InterEnemy enemy : this.list){
            listKOStatus.add(enemy.isKO());
        }

        return !listKOStatus.contains(false);
    }

}

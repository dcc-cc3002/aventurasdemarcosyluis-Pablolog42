package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.entities.enemies.InterEnemy;

import java.util.ArrayList;
import java.util.Random;

public class EnemyList {

    ArrayList<InterEnemy> list;

    public EnemyList(){
        list = new ArrayList<>();
    }

    public void addEnemy(InterEnemy enemy){
        this.list.add(enemy);
    }

    public InterEnemy retrieveRandomEnemy(){
        Random rand = new Random();
        return this.list.get(rand.nextInt(this.list.size()));
    }




    public void clearList(){
        for(InterEnemy enemy : this.list){
            list.remove(enemy);
        }
    }



}

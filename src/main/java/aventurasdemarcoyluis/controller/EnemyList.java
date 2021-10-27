package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.entities.enemies.EnemyType;
import aventurasdemarcoyluis.entities.enemies.InterEnemy;

import java.util.ArrayList;
import java.util.Random;

public class EnemyList {

    private GameController controller;
    ArrayList<InterEnemy> list;

    public EnemyList(){
        this.list = new ArrayList<>();
        this.controller = new GameController();
    }

    public void addRandomEnemies(int enemiesToAdd){
        for(int i=0; i<enemiesToAdd; i++){
            list.add(controller.createRandomStatsEnemy(EnemyType.getRandomEnemyType()));
        }
    }

    public void addEnemy(InterEnemy enemy){
        this.list.add(enemy);
    }

    public InterEnemy retrieveRandomEnemy(){
        Random rand = new Random();
        return this.list.get(rand.nextInt(this.list.size()));
    }




    public void clearList(){
        this.list.clear();
    }

    @Override
    public String toString(){
        String out = "";
        for(InterEnemy enemy : this.list){
            out = out.concat("\n" + enemy.toString());
        }
        return out;
    }



}

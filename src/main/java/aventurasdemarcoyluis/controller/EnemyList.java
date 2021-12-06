package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.enemies.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that represents the list of enemies to fight in a given battle.
 * Implemented with the use of an ArrayList of Enemies.
 */
public class EnemyList {


    private final ArrayList<InterEnemy> list;

    private GameController controller;

    /**
     * EnemyList Constructor.
     * Every EnemyList starts as an empty Arraylist.
     **/
    public EnemyList(GameController controller) {
        this.list = new ArrayList<>();
        this.controller = controller;
    }

    /**
     * Adds a given ammount of randomly selected enemies to the list.
     * These enemies also have random stats, as implemented in the createRandomStatsEnemy() method.
     *
     * @param enemiesToAdd the number of random enemies to add.
     */
    public void addRandomEnemies(int enemiesToAdd) {
        for (int i = 0; i < enemiesToAdd; i++) {
            list.add(createRandomStatsEnemy(EnemyType.getRandomEnemyType()));
        }
    }

    /**
     * Gets a random enemy from the enemyList.
     *
     * @return the random InterEnemy selected from the list.
     */
    public InterEnemy retrieveRandomEnemy() {
        Random rand = new Random();
        return this.list.get(rand.nextInt(this.list.size()));
    }

    /**
     * Gets a specific enemy (by index) from the EnemyList
     *
     * @param i The integer index of the enemy to retrieve from the list.
     * @return The InterEnemy of the enemyList correlated to the given index.
     */
    public InterEnemy retrieveEnemy(int i) {
        return this.list.get(i);
    }


    /**
     * Clears the current EnemyList. (removes all enemies)
     */
    public void clearList() {
        this.list.clear();
    }


    /**
     * Creates a random-stats enemy of the given type.
     * All the random stat values range from 1-16, with 16 being the max value posible,
     * Makes sure that the invariants hp < maxHP and fp < maxFP are always true.
     * In further implementations, the "bound" variable could define the game's difficulty.
     *
     * @param type The kind (EnemyType) of random enemy to create
     * @return A random-stat InterEnemy of the kind requested.
     */
    public InterEnemy createRandomStatsEnemy(@NotNull EnemyType type) {

        // Generates an array with 4 random stat values
        int[] stats = new int[4];
        int bound = 16;

        for (int i = 0; i <= 3; i++) {
            stats[i] = ThreadLocalRandom.current().nextInt(1, bound);
        }

        switch (type) {
            // Note: MAXHP has to be grater than hp, and hp has to be grater than 1
            case GOOMBA -> {
                return new Goomba(stats[0], stats[1], stats[2] + 1, stats[2] + 2, stats[3], this.controller);
            }
            case BOO -> {
                return new Boo(stats[0], stats[1], stats[2] + 1, stats[2] + 2, stats[3], this.controller);
            }
            case SPINY -> {
                return new Spiny(stats[0], stats[1], stats[2] + 1, stats[2] + 2, stats[3], this.controller);
            }
        }
        return null;
    }

    /**
     * Checks if all the enemies in the EnemyList are KO.
     *
     * @return Returns true if all enemies in the list are KO, false otherwise.
     */
    public boolean isListKO() {
        ArrayList<Boolean> listKOStatus = new ArrayList<>();
        for (InterEnemy enemy : this.list) {
            listKOStatus.add(enemy.isKO());
        }

        return !listKOStatus.contains(false);
    }

    /**
     * String representation of the enemyList, indicating every enemy in the list.
     * Overrides toString() in Object Class.
     *
     * @return String representation of the enemyList.
     */
    @Override
    public String toString() {
        String out = "";
        for (InterEnemy enemy : this.list) {
            out = out.concat(enemy.toString() + "\n");
        }
        if (out.equals("")) return "No enemies in enemy list";
        return out;
    }

    public ArrayList<InterEnemy> getList() {
        return list;
    }
}

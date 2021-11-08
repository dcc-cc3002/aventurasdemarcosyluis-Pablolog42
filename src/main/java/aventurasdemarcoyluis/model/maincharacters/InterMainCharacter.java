package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.InterItem;


public interface InterMainCharacter extends InterEntity {
    /**
     * Receives the DD call of an enemy attacking.
     * @param enemy The enemy Attacking
     */
    void enemyAttacking(InterEnemy enemy);

    /**
     * Restores a certain amount of FP to a MainCharacter.
     * @param fpToRestore the FP to restore.
     */
    void restoreFP(int fpToRestore);

    /**
     * Uses a given item.
     * @param item the item to use.
     */
    void useItem(InterItem item);

    /**
     * Jump-Attacks an enemy.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    void jumpAttack(InterEnemy enemy);

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    void hammerAttack(InterEnemy enemy);
    //** Gets the mainCharacter's fp **//
    int getFp();
    //** Gets the mainCharacter's maxFp **//
    int getMaxFP();
}

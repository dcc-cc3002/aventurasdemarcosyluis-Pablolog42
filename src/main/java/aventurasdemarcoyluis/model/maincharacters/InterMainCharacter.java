package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.InterItem;

//TODO: Comentar la interface
public interface InterMainCharacter extends InterEntity {
    void enemyAttacking(InterEnemy enemy);
    void restoreFP(int fpToRestore);
    void useItem(InterItem item);

    int getFp();

    int getMaxFP();

    /**
     * Jump-Attacks a Goomba.
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

}

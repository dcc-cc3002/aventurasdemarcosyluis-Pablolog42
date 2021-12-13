package aventurasdemarcoyluis.backend.model.maincharacters;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;

public interface InterMarco{
    /**
     * Jump-Attacks an enemy.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    void jumpAttack(InterEnemy enemy) throws InvalidAttackException;

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    void hammerAttack(InterEnemy enemy) throws InvalidAttackException;




}

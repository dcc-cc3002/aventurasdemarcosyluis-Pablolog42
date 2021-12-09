package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.model.enemies.InterGoombaSpiny;

public interface InterLuis extends InterMainCharacter{
    /**
     * Jump-Attacks an enemy.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    void jumpAttack(InterGoombaSpiny enemy) throws InvalidAttackException;

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    void hammerAttack(InterGoombaSpiny enemy) throws InvalidAttackException;
}

package aventurasdemarcoyluis.backend.model.enemies;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.InterEntity;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

/**
 * Interface denoting all the general enemies methods.
 */
public interface InterEnemy extends InterEntity {

    /**
     * Receives the DD call from a player Hammer-attacking.
     * @throws InvalidAttackException in case spiny dodges an attack.
     */
    void playerHammerAttacking(InterMainCharacter character) throws InvalidAttackException;

    /**
     * Receives the DD call from a player jump-attacking.
     */
    void playerJumpAttacking(InterMainCharacter character) throws InvalidAttackException;

    /**
     * Performs the DD call to attack a main character
     * @param involvedMainCharacter the character to attack
     * @throws InvalidAttackException in case the attack is invalid
     */
    void attack(InterMainCharacter involvedMainCharacter) throws InvalidAttackException;
}

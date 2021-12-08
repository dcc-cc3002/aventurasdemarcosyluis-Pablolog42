package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.maincharacters.InterLuis;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;


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

    void attack(InterMainCharacter involvedMainCharacter) throws InvalidAttackException;
}

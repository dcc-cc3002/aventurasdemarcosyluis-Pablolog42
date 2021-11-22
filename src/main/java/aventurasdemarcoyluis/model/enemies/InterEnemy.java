package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;


public interface InterEnemy extends InterEntity {
    /**
     * Attacks a player.
     *
     * @param player The player to attack.
     */
    void attack(InterMainCharacter player);

    /**
     * Receives the DD call from a player Hammer-attacking.
     */
    void playerHammerAttacking(InterMainCharacter character);

    /**
     * Receives the DD call from a player jump-attacking.
     */
    void playerJumpAttacking(InterMainCharacter character);


}

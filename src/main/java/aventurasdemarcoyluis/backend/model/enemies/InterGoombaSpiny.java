package aventurasdemarcoyluis.backend.model.enemies;

import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

/**
 * Interface denoting the beheabour of Goomba and Spiny enemies
 */
public interface InterGoombaSpiny extends InterEnemy{

    /**
     * Attacks a player.
     *
     * @param player The player to attack.
     */
    void attack(InterMainCharacter player);

}

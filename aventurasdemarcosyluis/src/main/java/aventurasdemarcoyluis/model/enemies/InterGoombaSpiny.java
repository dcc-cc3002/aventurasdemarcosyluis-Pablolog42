package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;


public interface InterGoombaSpiny extends InterEnemy{

    /**
     * Attacks a player.
     *
     * @param player The player to attack.
     */
    void attack(InterMainCharacter player);

}

package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

/*
    Goomba enemy Class
 */
public class Goomba extends AbstractEnemy implements InterGoombaSpiny {

    /**
     * Creates a new Goomba
     *  @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     * @param controller
     */
    public Goomba(double ATK, double DEF, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, HP, MAXHP, LVL, EntityType.GOOMBA);
    }

    /**
     * Sends the double dispatch attack message to a player.
     *
     * @param player The player being attacked.
     **/
    public void attack(@NotNull InterMainCharacter player) {
        player.enemyAttacking(this);
    }
}

package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

/*
    Goomba enemy Class
 */
public class Goomba extends AbstractEnemy {

    /**
     * Creates a new Goomba
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     */
    public Goomba(double ATK, double DEF, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, HP, MAXHP, LVL, EntityType.GOOMBA);
    }

    /**
     *  Sends the double dispatch attack message to a player.
     * @param player The player being attacked.
     *
     **/
    @Override
    public void attack(InterMainCharacter player){
        player.enemyAttacking(this);
    }
}

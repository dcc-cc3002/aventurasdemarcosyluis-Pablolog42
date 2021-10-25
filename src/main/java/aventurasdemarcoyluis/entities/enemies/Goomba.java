package aventurasdemarcoyluis.entities.enemies;

import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;

/*
    Goomba enemy Class
 */
public class Goomba extends AbstractEnemy {

    /**
     * Creates a new Goomba
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     */
    public Goomba(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.GOOMBA);
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

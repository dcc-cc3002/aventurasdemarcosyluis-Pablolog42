package aventurasdemarcoyluis.enemies;

import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.players.AbstractPlayer;

public class Spiny extends AbstractEnemy{


    /**
     * Creates a new Spiny
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public Spiny(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, String NAME) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.SPINY, NAME);
    }

    // when jump-attacking spinny, the player does 0 dmg and looses 5% of their MAX HP.
    @Override
    public void playerJumpAttacking(AbstractPlayer player) {
        player.receiveDamage(player.getMaxHP()*0.05);
    }

}

package aventurasdemarcoyluis.enemies;

import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.players.AbstractPlayer;

/*
    Spiny enemy Class
 */
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
    /**
     *  Receives the double dispatch call sent from an AbstractPlayer.
     *  In this case (Spiny being Jump-attacked), Spiny will always dodge the attacker take any dmg from the player,
     *  and will instead infringe 5% of the players' maxHP as backlash damage.
     *
     * @param player The player sending the Jump-attack Message
     **/
    @Override
    public void playerJumpAttacking(AbstractPlayer player) {
        player.receiveDamage(player.getMaxHP()*0.05);
    }

}

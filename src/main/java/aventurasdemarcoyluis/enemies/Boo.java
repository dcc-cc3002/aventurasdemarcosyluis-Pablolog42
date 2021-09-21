package aventurasdemarcoyluis.enemies;

import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.players.AbstractPlayer;

public class Boo extends AbstractEnemy {


    /**
     * Creates a new Boo
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public Boo(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, String NAME) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.BOO, NAME);
    }

    // Boo dodges hammer attack
    @Override
    public void playerHammerAttacking(AbstractPlayer player) {
        System.out.println(this.getName() + " has dodged " + player.getName() + "'s attack!");;
    }



    }


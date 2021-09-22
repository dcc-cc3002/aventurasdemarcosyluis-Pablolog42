package aventurasdemarcoyluis.enemies;


import aventurasdemarcoyluis.AbstractEntity;
import aventurasdemarcoyluis.players.AbstractPlayer;
import aventurasdemarcoyluis.EntityType;

public abstract class AbstractEnemy extends AbstractEntity {


    /**
     * Creates a new AbstractEnemy
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     * @param TYPE  type of the enemy (see enum "EnemyType")
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public AbstractEnemy(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, EntityType TYPE, String NAME) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, TYPE, NAME);
    }

    // TODO COMENTAR ESTO
    public void playerHammerAttacking(AbstractPlayer player) {
        this.receiveDamage(this.computeDmg(1.5, player));
    }
    // TODO COMENTAR ESTO
    public void playerJumpAttacking(AbstractPlayer player) {
        this.receiveDamage(this.computeDmg(1,player));
    }








}

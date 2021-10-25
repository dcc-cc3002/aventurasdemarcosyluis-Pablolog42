package aventurasdemarcoyluis.entities.enemies;


import aventurasdemarcoyluis.entities.AbstractEntity;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;


/*
    Abstract representation of an Enemy.
    An enemy is a specific kind of Entity.
 */
public abstract class AbstractEnemy extends AbstractEntity implements InterEnemy {


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
     */
    public AbstractEnemy(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, EntityType TYPE) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, TYPE);
    }

    /**
     *  Receives the double dispatch call sent from an AbstractMainCharacter.
     *  After that, infringes DMG according to the Hammer-Attack constant (k=1.5)
     *
     * @param player The player sending the attack Message
     *
     **/
    public void playerHammerAttacking(InterMainCharacter player) {
        this.receiveDamage(this.computeDmg(1.5, player));
    }

    /**
     *  Receives the double dispatch call sent from an AbstractMainCharacter.
     *  After that, infringes DMG according to the Jump-Attack constant (k=1)
     *
     * @param player The player sending the attack Message
     *
     **/
    public void playerJumpAttacking(InterMainCharacter player) {
        this.receiveDamage(this.computeDmg(1,player));
    }








}

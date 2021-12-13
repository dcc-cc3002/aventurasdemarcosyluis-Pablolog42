package aventurasdemarcoyluis.backend.model.maincharacters;


import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;

/**
    Marco player Class
 */
public class Marco extends AbstractMainCharacter implements InterMarco{
    /**
     * Creates a new AbstractEntity
     *  @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     * @param controller
     */
    public Marco(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.MARCO);
    }

    /**
     * Jump-Attacks a Goomba.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void jumpAttack(InterEnemy enemy) throws InvalidAttackException {
        this.jumpAttackAction(enemy);
    }

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void hammerAttack(InterEnemy enemy) throws InvalidAttackException {
        this.hammerAttackAction(enemy);
    }

}

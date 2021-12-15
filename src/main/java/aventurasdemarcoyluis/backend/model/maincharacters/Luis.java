package aventurasdemarcoyluis.backend.model.maincharacters;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.InterGoombaSpiny;

/**
    Luis player Class
 */
public class Luis extends AbstractMainCharacter implements InterLuis {
    /**
     * Creates a new AbstractEntity
     *  @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit

     */
    public Luis(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.LUIS);
    }



    /**
     * Jump-Attacks an enemy.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    @Override
    public void jumpAttack(InterGoombaSpiny enemy) throws InvalidAttackException {
        super.jumpAttackAction(enemy);
    }

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    @Override
    public void hammerAttack(InterGoombaSpiny enemy) throws InvalidAttackException {
        this.hammerAttackAction(enemy);
    }
}
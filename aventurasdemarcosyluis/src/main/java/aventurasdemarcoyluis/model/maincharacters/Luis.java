package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.InterGoombaSpiny;

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
     * @param controller
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
        this.jumpAttackAction(enemy);
    }
}
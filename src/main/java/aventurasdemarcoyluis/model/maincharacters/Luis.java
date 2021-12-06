package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.EntityType;

/*
    Luis player Class
 */
public class Luis extends AbstractMainCharacter {
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
    public Luis(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, GameController controller) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.LUIS, controller);
    }


}
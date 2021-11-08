package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.*;

/*
    Luis player Class
 */
public class Luis extends AbstractMainCharacter{
    /**
     * Creates a new AbstractEntity
     *
     * @param ATK   jumpAttack points
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






}
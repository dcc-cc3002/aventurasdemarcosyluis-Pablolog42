package aventurasdemarcoyluis;


public class Marco extends AbstractPlayer {

    /**
     * Creates a new AbstractPlayer
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP
     * @param HP    heal points
     * @param MAXHP
     * @param LVL
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public Marco(double ATK, double DEF, double FP, double HP, double MAXHP, int LVL, String NAME) {
        super(ATK, DEF, FP, HP, MAXHP, LVL, EntityType.MARCO, NAME);
    }


}

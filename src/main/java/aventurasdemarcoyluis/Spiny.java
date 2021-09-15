package aventurasdemarcoyluis;

public class Spiny extends AbstractEnemy{
    /**
     * Creates a new AbstractEnemy
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    Magic (mana) points (?
     * @param HP    heal points
     * @param MAXHP
     * @param LVL   level of the Unit
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public Spiny(double ATK, double DEF, double FP, double HP, double MAXHP, int LVL,  String NAME) {
        super(ATK, DEF, FP, HP, MAXHP, LVL, EntityType.SPINY, NAME);
    }
}

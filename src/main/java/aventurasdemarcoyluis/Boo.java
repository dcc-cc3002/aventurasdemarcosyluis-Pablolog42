package aventurasdemarcoyluis;

public class Boo extends AbstractEnemy {
    /**
     * Creates a new BOO
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP
     * @param HP    heal points
     * @param MAXHP
     * @param LVL   level of the Unit
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public Boo(double ATK, double DEF, double FP, double HP, double MAXHP, int LVL,  String NAME) {
        super(ATK, DEF, FP, HP, MAXHP, LVL, EntityType.BOO, NAME);
    }


    }


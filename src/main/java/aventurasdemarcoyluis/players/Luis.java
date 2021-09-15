package aventurasdemarcoyluis.players;

import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.enemies.Boo;
import aventurasdemarcoyluis.enemies.Goomba;
import aventurasdemarcoyluis.enemies.Spiny;

public class Luis extends AbstractPlayer {
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
    public Luis(double ATK, double DEF, int FP, double HP, double MAXHP, int LVL, String NAME) {
        super(ATK, DEF, FP, HP, MAXHP, LVL, EntityType.LUIS, NAME);
    }


    // Attacks





    // Attackers
    public void goombaAttacking(Goomba goomba){
        this.enemyAttacking(goomba);
    }

    public void spinyAttacking(Spiny spiny){
        this.enemyAttacking(spiny);
    }

    public void booAttacking(Boo boo){
        this.enemyAttacking(boo);
    }
}
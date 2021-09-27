package aventurasdemarcoyluis.players;


import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.enemies.*;

/*
    Marco player Class
 */
public class Marco extends AbstractPlayer {
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
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public Marco(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, String NAME) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.MARCO, NAME);
    }


    // Attacks



    // Attackers
    /**
     * Receives the double dispatch attack message from a Goomba enemy.
     *
     * @param goomba The Goomba sending the attack msg.
     **/
    public void goombaAttacking(Goomba goomba){
        this.enemyAttacking(goomba);
    }

    /**
     * Receives the double dispatch attack message from a Spiny enemy.
     *
     * @param spiny The Spiny sending the attack msg.
     **/
    public void spinyAttacking(Spiny spiny){
        this.enemyAttacking(spiny);
    }

    /**
     * Receives the double dispatch attack message from a Boo enemy.
     *
     * As boo doesn't attack Marcos, the double dispatch call is ignored, and a console message is printed instead.
     *
     * @param boo The boo sending the attack msg.
     **/
    public void booAttacking(Boo boo){
        System.out.println(boo.getName() + " can't attack Marcos!");
    }


}

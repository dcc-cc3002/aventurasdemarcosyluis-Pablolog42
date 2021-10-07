package aventurasdemarcoyluis.entities.maincharacters;


import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.enemies.*;

/*
    Marco player Class
 */
public class Marco extends AbstractMainCharacter {
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
    public Marco(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.MARCO);
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

package aventurasdemarcoyluis.model.maincharacters;


import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.*;

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


    // Attackers

    // Note: Marco can't be attacked by Boo. Thus, attack methods have to be segregated by character.
    // Action methods do the attack, and are segregated by "attack" methods.
    // This is so something like "boo.enemyAttack(Marco)" won't compile.

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



}

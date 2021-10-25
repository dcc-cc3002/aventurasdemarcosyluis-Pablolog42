package aventurasdemarcoyluis.entities.maincharacters;


import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.enemies.*;

import java.util.Random;

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

    /**
     * Jump-Attacks a Goomba.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void jumpAttack(InterEnemy enemy){
        this.jumpAttackAction(enemy);
    }

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void hammerAttack(InterEnemy enemy){
        this.hammerAttackAction(enemy);
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

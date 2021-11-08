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


    // Attacks

    // Note: Luis Can't attack Boo. Thus, attack methods have to be segregated by character.
    // Action methods do the attack, and are segregated by "attack" methods.
    // This is so something like "luis.hammerAttack(boo)" won't compile.

    /**
     * Jump-Attacks a Goomba.
     * @param enemy The Goomba to send the attack message to
     */
    public void jumpAttack(Goomba enemy){
        this.jumpAttackAction(enemy);
    }

    /**
     * Jump-Attacks a Spiny.
     * @param enemy The Spiny to send the attack message to
     */
    public void jumpAttack(Spiny enemy){
        this.jumpAttackAction(enemy);
    }

    /**
     * Hammer-Attacks a Goomba.
     * @param enemy The Goomba to send the attack message to
     */
    public void hammerAttack(Goomba enemy){
        this.hammerAttackAction(enemy);
    }

    /**
     * Hammer-Attacks a Spiny.
     * @param enemy The Spiny to send the attack message to
     */
    public void hammerAttack(Spiny enemy){
        this.hammerAttackAction(enemy);
    }


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


}
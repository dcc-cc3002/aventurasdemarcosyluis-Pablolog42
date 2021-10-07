package aventurasdemarcoyluis.entities.enemies;

import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;

/*
    Boo enemy Class
 */
public class Boo extends AbstractEnemy {


    /**
     * Creates a new Boo
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     */
    public Boo(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, EntityType.BOO);
    }

    // Boo dodges hammer attack
    /**
     *  Receives the double dispatch call sent from an AbstractMainCharacter.
     *  In this case (Boo being Hammer-attacked), Boo will always dodge the attack, not infringing any damage to them.
     *
     * @param player The player sending the Hammer-attack Message
     *
     **/
    @Override
    public void playerHammerAttacking(AbstractMainCharacter player) {
        System.out.println(this.getName() + " has dodged " + player.getName() + "'s attack!");;
    }



    }


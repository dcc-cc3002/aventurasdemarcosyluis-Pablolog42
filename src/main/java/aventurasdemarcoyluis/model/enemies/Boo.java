package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

/*
    Boo enemy Class
 */
public class Boo extends AbstractEnemy {


    /**
     * Creates a new Boo
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     */
    public Boo(double ATK, double DEF, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, HP, MAXHP, LVL, EntityType.BOO);
    }

    /**
     *  Sends the double dispatch attack message to a player.
     * @param player The player being attacked.
     * Note: In this case, Boo can only attack "Luis" Main Character.
     *
     **/
    @Override
    public void attack(InterMainCharacter player){
        player.enemyAttacking(this);
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
    public void playerHammerAttacking(InterMainCharacter player) {
        System.out.println(this.getName() + " has dodged " + player.getName() + "'s attack!");
    }



    }


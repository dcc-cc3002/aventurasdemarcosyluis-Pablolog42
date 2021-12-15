package aventurasdemarcoyluis.backend.model.enemies;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterLuis;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

/**
    Boo enemy Class
 */
public class Boo extends AbstractEnemy implements InterBoo {


    /**
     * Creates a new Boo
     *  @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     */
    public Boo(double ATK, double DEF, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, HP, MAXHP, LVL, EntityType.BOO);
    }



    // Boo dodges hammer attack

    /**
     * Receives the double dispatch call sent from an AbstractMainCharacter.
     * In this case (Boo being Hammer-attacked), Boo will always dodge the attack, not infringing any damage to them.
     *
     * @param player The player sending the Hammer-attack Message
     **/
    @Override
    public void playerHammerAttacking(@NotNull InterMainCharacter player) throws InvalidAttackException {
        throw new InvalidAttackException(this.getName() + " has dodged " + player.getName() + "'s attack!");
    }

    /**
     * sends the dd to attack
     * @param involvedMainCharacter the character to attack
     * @throws InvalidAttackException in case the attack is invalid
     */
    @Override
    public void attack(InterMainCharacter involvedMainCharacter) throws InvalidAttackException {
        if(involvedMainCharacter.getType()==EntityType.MARCO){
            throw new InvalidAttackException("Boo can't attack marco!");
        }
        involvedMainCharacter.enemyAttacking(this);
    }


}


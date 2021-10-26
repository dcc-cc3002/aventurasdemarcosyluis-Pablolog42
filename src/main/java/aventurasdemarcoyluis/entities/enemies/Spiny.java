package aventurasdemarcoyluis.entities.enemies;

import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

/*
    Spiny enemy Class
 */
public class Spiny extends AbstractEnemy{

    /**
     * Creates a new Spiny
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     */
    public Spiny(double ATK, double DEF, double HP, double MAXHP, int LVL) {
        super(ATK, DEF, HP, MAXHP, LVL, EntityType.SPINY);
    }

    /**
     *  Sends the double dispatch attack message to a player.
     * @param player The player being attacked.
     *
     **/
    @Override
    public void attack(@NotNull InterMainCharacter player){
        player.enemyAttacking(this);
    }


    // when jump-attacking spinny, the player does 0 dmg and looses 5% of their MAX HP.
    /**
     *  Receives the double dispatch call sent from an AbstractMainCharacter.
     *  In this case (Spiny being Jump-attacked), Spiny will always dodge the attacker take any dmg from the player,
     *  and will instead infringe 5% of the maincharacters' maxHP as backlash damage.
     *
     * @param player The player sending the Jump-attack Message
     **/
    @Override
    public void playerJumpAttacking(@NotNull InterMainCharacter player) {
        player.receiveDamage(player.getMaxHP()*0.05);
    }



}

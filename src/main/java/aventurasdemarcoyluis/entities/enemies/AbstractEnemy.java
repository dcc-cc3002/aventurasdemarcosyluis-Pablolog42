package aventurasdemarcoyluis.entities.enemies;


import aventurasdemarcoyluis.entities.AbstractEntity;
import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.InterEntity;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;


/*
    Abstract representation of an Enemy.
    An enemy is a specific kind of Entity.
 */
public abstract class AbstractEnemy extends AbstractEntity implements InterEnemy {


    /**
     * Creates a new AbstractEnemy
     * enemies don't have FP.
     * @param ATK jumpAttack points
     * @param DEF defense points
     * @param HP  health points
     * @param MAXHP Maximum HP of the unit
     * @param LVL  level of the Unit
     * @param TYPE type of the enemy (see enum "EnemyType")
     */
    public AbstractEnemy(double ATK, double DEF, double HP, double MAXHP, int LVL, EntityType TYPE) {
        super(ATK, DEF, HP, MAXHP, LVL, TYPE);
    }

    @Override
    public double computeDmg(double k, @NotNull InterEntity attacker){
        return (k * attacker.getAtk() * attacker.getLvl()) / this.getDef();
    }

    /**
     *  Receives the double dispatch call sent from an AbstractMainCharacter.
     *  After that, infringes DMG according to the Hammer-Attack constant (k=1.5)
     *
     * @param player The player sending the attack Message
     *
     **/
    @Override
    public void playerHammerAttacking(InterMainCharacter player) {
        this.receiveDamage(this.computeDmg(1.5, player));
    }

    /**
     *  Receives the double dispatch call sent from an AbstractMainCharacter.
     *  After that, infringes DMG according to the Jump-Attack constant (k=1)
     *
     * @param player The player sending the attack Message
     *
     **/
    @Override
    public void playerJumpAttacking(InterMainCharacter player) {
        this.receiveDamage(this.computeDmg(1,player));
    }

    @Override
    public String toString(){
        return this.getType().toString() + " with stats:   " + "| ATK: " + this.getAtk() + " | DEF: " + this.getDef() + " | HP: " + this.getHp() + " | MAXHP: " + this.getMaxHP() + " | LVL: " + this.getLvl() + " |";
    }

}

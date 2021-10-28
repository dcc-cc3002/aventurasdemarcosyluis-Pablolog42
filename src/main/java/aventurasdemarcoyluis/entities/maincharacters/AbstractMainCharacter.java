package aventurasdemarcoyluis.entities.maincharacters;

import aventurasdemarcoyluis.entities.AbstractEntity;
import aventurasdemarcoyluis.entities.enemies.InterEnemy;
import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.items.InterItem;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/*
    Abstract representation of a Player.
    A player is a specific kind of Entity.
 */
public abstract class AbstractMainCharacter extends AbstractEntity implements InterMainCharacter {


    private int fp;
    private int maxFP;

    /**
     * Creates a new AbstractEntity
     *
     * @param ATK   Attack points
     * @param DEF   defense points
     * @param FP    FP
     * @param MAXFP Maximum FP points for the unit
     * @param HP    heal points
     * @param MAXHP Maximum HP points for the unit
     * @param LVL   level of the Unit
     * @param TYPE  type of the enemy (see enum "EnemyType")
     */
    public AbstractMainCharacter(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, EntityType TYPE) {
        super(ATK, DEF, HP, MAXHP, LVL, TYPE);
        this.fp = FP;
        this.maxFP = MAXFP;
    }

    /**
     * Restores an amount of FP to an entity
     * In case the targetFP exceeds the entity's maxHP, sets fp = maxFP
     *
     * @param fpToRestore The amount of HP to restore to the entity.
     */
    @Override
    public void restoreFP(int fpToRestore) {
        int targetFP = this.fp + fpToRestore;
        // FP can't be more than maxFP
        if (this.maxFP < targetFP) {
            this.setFp(maxFP);
            return;
        }
        this.setFp(targetFP);
    }


    // Do attacks

    /**
     * Jump-Attacks an Enemy.
     * Is segregated by "jumpAttack" method in all subclasses. (Done through selectable calling of this method)
     *
     * Checks if the player is KO (and thus, if they can attack)
     * In case they can, checks if they have enough FP
     * Finally, sends the double dispatch message to the enemy.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void jumpAttackAction(InterEnemy enemy){
        int fpCost = 1;
        int targetFP = this.getFp()-fpCost;
        // In case the attack is not a legal move, exit the method
        if(!validateAttack(fpCost)){ return; }

        this.setFp(targetFP);
        enemy.playerJumpAttacking(this);
    }


    /**
     * Hammer-Attacks an enemy.
     *
     * Is segregated by "jumpAttack" method in all subclasses. (Done through selectable calling of this method)
     *
     * Checks if the player is KO (and thus, if they can attack)
     * In case they can, checks if they have enough FP
     * Afterwards, checks if the attack has failed (25% probability of failing)
     * Finally, sends the double dispatch message to the enemy.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void hammerAttackAction(InterEnemy enemy){
        int fpCost = 2;
        int targetFP = this.getFp()-fpCost;

        // In case the attack is not a legal move, exit the method
        if(!validateAttack(fpCost)){ return; }

        // 25% of failing: 0,1,2,3 possible outcomes. 0 is a fail.
        Random rand = new Random();
        int int_random = rand.nextInt(4);
        if(int_random == 0) {
            System.out.println("Hammer Attack has Failed!");
            return;
        }

        this.setFp(targetFP);

        enemy.playerHammerAttacking(this);
    }


    /**
     * Checks if an attack is a legal move.
     * Checks if the player attacking is KO
     * checks if the player attacking has enough FP
     * @param fpCost The FP cost of the attack to perform
     * @return A boolean indicating if the attack is valid or not.
     */
    public boolean validateAttack(int fpCost) {

        int targetFP = this.getFp() - fpCost;

        if (this.isKO()) {
            System.out.println(this.getName() + " is K.O. and can't attack");
            return false;
        }

        if (targetFP < 0) {
            System.out.println("Not enough FP!");
            return false;
        }
        return true;
    }


    // Receive Attacks

    /**
     * Receives the double dispatch message from an enemy attacking
     * Infringes dmg to the player according to the dmg formula, with a k=0.75
     *
     * @param enemy The enemy that is sending the attack message
     */
    @Override
    public void enemyAttacking(InterEnemy enemy) {
        this.receiveDamage(this.computeDmg(0.75, enemy));
    }


    // Items

    // Sends DD message to item to be used.
    // note: at this point, the player always has the item to be used.
    public void useItem(@NotNull InterItem item){
        item.useItem(this);
    }


    // Setters and getters

    /** Gets the current FP of an entity **/
    public int getFp() {
        return fp;
    }
    /** Gets the current HP of an entity **/
    public double getMaxFP() {
        return maxFP;
    }
    /** Sets the current FP of an entity **/
    public void setFp(int fp) {
        this.fp = fp;
    }



    @Override
    public String toString(){
        String notKo = this.getType().toString() + " with stats:   " +
                "| ATK: " + this.getAtk() +
                " | DEF: " + this.getDef() +
                " | HP: " + this.getHp() +
                " | MAXHP: " + this.getMaxHP() +
                " | FP: " + this.getFp() +
                " | MAXFP: " + this.getMaxFP() +
                " | LVL: " + this.getLvl() + " |";

        String ko = "%%% K.O. %%% " + notKo;

        return this.isKO()? ko:notKo;
    }
}



package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.model.AbstractEntity;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.InterItem;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/*
    Abstract representation of a MainCharacter.
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
     * <p>
     * Checks if the player is KO (and thus, if they can attack)
     * In case they can, checks if they have enough FP
     * Finally, sends the double dispatch message to the enemy.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void jumpAttackAction(InterEnemy enemy) throws InvalidAttackException {
        int fpCost = 1;
        int targetFP = this.getFp() - fpCost;
        // In case the attack is not a legal move, exit the method

        validateAttack(fpCost);


        this.setFp(targetFP);
        enemy.playerJumpAttacking(this);
    }


    /**
     * Hammer-Attacks an enemy.
     * <p>
     * Is segregated by "jumpAttack" method in all subclasses. (Done through selectable calling of this method)
     * <p>
     * Checks if the player is KO (and thus, if they can attack)
     * In case they can, checks if they have enough FP
     * Afterwards, checks if the attack has failed (25% probability of failing)
     * Finally, sends the double dispatch message to the enemy.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void hammerAttackAction(InterEnemy enemy) throws InvalidAttackException {
        int fpCost = 2;
        int targetFP = this.getFp() - fpCost;

        validateAttack(fpCost);

        // 25% of failing: 0,1,2,3 possible outcomes. 0 is a fail.
        Random rand = new Random();
        int int_random = rand.nextInt(4);
        if (int_random == 0) {
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
     *
     * @param fpCost The FP cost of the attack to perform
     * @return A boolean indicating if the attack is valid or not.
     */
    public void validateAttack(int fpCost) throws InvalidAttackException {

        int targetFP = this.getFp() - fpCost;

        if (this.isKO()) {
            throw new InvalidAttackException(this.getName() + " is K.O. and can't attack");
        }

        if (targetFP < 0) {
            throw new InvalidAttackException(this.getName() + " has not enough FP to make this kind of attack!");
        }
    }



    /**
     * Checks if an attack is a legal move.
     * Checks if the player attacking is KO
     * checks if the player attacking has enough FP
     *
     * Throws exception in case any of the above conditions are encountered.
     *
     */
    public void validateAttack(AttackType attackType) throws InvalidAttackException {

        int fpCost=0;

        switch (attackType){
            case JUMP -> fpCost = 1;
            case HAMMER -> fpCost = 2;
        }

        int targetFP = this.getFp() - fpCost;

        if (this.isKO()) {
            throw new InvalidAttackException(this.getName() + " is K.O. and can't attack");
        }

        if (targetFP < 0) {
            throw new InvalidAttackException(this.getName() + " has not enough FP to make this kind of attack!");
        }
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


    /**
     * Sends DD message to item to be used.
     * note: at this point, the player always has the item to be used.
     *
     * @param item the Item to be used
     */
    @Override
    public void useItem(@NotNull InterItem item) {
        item.useItem(this);
    }

    /**
     * Jump-Attacks a Goomba.
     * Sends DD message to jumpAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void jumpAttack(InterEnemy enemy) throws InvalidAttackException {
        this.jumpAttackAction(enemy);
    }

    /**
     * Hammer-Attacks an enemy.
     * Sends DD message to hammerAttack() method.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void hammerAttack(InterEnemy enemy) throws InvalidAttackException {
        this.hammerAttackAction(enemy);
    }

    /**
     * Executes the lvlUp routine for the MainCharacter, increasing all stats according to the given level.
     */
    public void lvlUp() {
        // Level Up!
        this.setLvl(this.getLvl() + 1);
        // Increase 15% of every stat
        this.setMaxHP((115.0 / 100) * this.getMaxHP());
        this.setMaxFP((int) ((115.0 / 100) * this.getMaxFP()));
        this.setAtk((115.0 / 100) * this.getAtk());
        this.setDef((115.0 / 100) * this.getDef());

    }

    // Setters and getters

    /**
     * Gets the current FP of an entity
     **/
    @Override
    public int getFp() {
        return fp;
    }

    /**
     * Gets the current HP of an entity
     **/
    @Override
    public int getMaxFP() {
        return maxFP;
    }

    /**
     * Sets the current FP of an entity
     **/
    public void setFp(int fp) {
        this.fp = fp;
    }

    /**
     * Sets the maxFP of an entity
     **/
    public void setMaxFP(int maxFP) {
        this.maxFP = maxFP;
    }


    /**
     * Provides a string representation of the current entity.
     * Overrides "toString" method in Object Class.
     *
     * @return a string representation of the current entity.
     */
    @Override
    public String toString() {
        String notKo = this.getType().toString() + " with stats:   " +
                "| ATK: " + this.getAtk() +
                " | DEF: " + this.getDef() +
                " | HP: " + this.getHp() +
                " | MAXHP: " + this.getMaxHP() +
                " | FP: " + this.getFp() +
                " | MAXFP: " + this.getMaxFP() +
                " | LVL: " + this.getLvl() + " |";

        String ko = "%%% K.O. %%% " + notKo;

        return this.isKO() ? ko : notKo;
    }


}



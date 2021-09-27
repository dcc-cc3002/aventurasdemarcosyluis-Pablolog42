package aventurasdemarcoyluis.players;

import aventurasdemarcoyluis.AbstractEntity;
import aventurasdemarcoyluis.items.AbstractItem;
import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.enemies.AbstractEnemy;
import aventurasdemarcoyluis.items.ItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    Abstract representation of a Player.
    A player is a specific kind of Entity.
 */
public abstract class AbstractPlayer extends AbstractEntity {

    // Empty array
    private List<AbstractItem> armamento;
    protected boolean isInvincible = false;

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
     * @param TYPE  type of the enemy (see enum "EnemyType")
     * @param NAME  name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public AbstractPlayer(double ATK, double DEF, int FP, int MAXFP, double HP, double MAXHP, int LVL, EntityType TYPE, String NAME) {
        super(ATK, DEF, FP, MAXFP, HP, MAXHP, LVL, TYPE, NAME);
        armamento = new ArrayList<>();
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

    // Do Attack

    /**
     * Hammer-Attacks an enemy.
     * Checks if the player is KO (and thus, if they can attack)
     * In case they can, checks if they have enough FP
     * Afterwards, checks if the attack has failed (25% probability of failing)
     * Finally, sends the double dispatch message to the enemy.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void hammerAttack(AbstractEnemy enemy){
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
     * Jump-Attacks an enemy.
     * Checks if the player is KO (and thus, if they can attack)
     * In case they can, checks if they have enough FP
     * Finally, sends the double dispatch message to the enemy.
     *
     * @param enemy The enemy to send the attack message to
     */
    public void jumpAttack(AbstractEnemy enemy){
        int fpCost = 1;
        int targetFP = this.getFp()-fpCost;

        // In case the attack is not a legal move, exit the method
        if(!validateAttack(fpCost)){ return; }

        this.setFp(targetFP);

        enemy.playerJumpAttacking(this);
    }


    // Receive Attacks

    /**
     * Receives the double dispatch message from an enemy attacking
     * Infringes dmg to the player according to the dmg formula, with a k=0.75
     *
     * @param enemy The enemy that is sending the attack message
     */
    public void enemyAttacking(AbstractEnemy enemy) {
        this.receiveDamage(this.computeDmg(0.75, enemy));
    }




    // Items

    /**
     * Adds an item object to a players' armament.
     * *
     * @param item the item to add.
     */
    public void addAnItem(AbstractItem item){
        armamento.add(item);
    }

    /**
     * Sends the message to use an item from a players' armament.
     * Checks if the player has an item of a certain kind in their armament.
     * in case they do, consumes the item (removes it from the players' inventory) and uses it.
     * @param itemType the type of Item to use.
     */
    public void useItem(ItemType itemType){
        // Check if the player has an item of the kind we want to use in their armamento
        for(AbstractItem armamentoItem : armamento){
            if (armamentoItem.getType() == itemType) {
                armamentoItem.useItem(this);
                // We remove the item when used
                armamento.remove(armamentoItem);
                return;
            }
        }
        System.out.println(this.getName() + " doesn't have a/an " + itemType + " in their inventory!");

    }

    // Getters and setters

    /* Sets a player into invincible mode*/
    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }
    /* Gets the players' invincible mode status.*/
    public boolean isInvincible() {
        return isInvincible;
    }
    /* Gets the players' Armament.*/
    public List<AbstractItem> getArmamento() {
        return armamento;
    }
}



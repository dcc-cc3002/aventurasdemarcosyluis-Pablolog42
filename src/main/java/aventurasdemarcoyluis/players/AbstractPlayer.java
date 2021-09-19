package aventurasdemarcoyluis.players;

import aventurasdemarcoyluis.AbstractEntity;
import aventurasdemarcoyluis.items.AbstractItem;
import aventurasdemarcoyluis.EntityType;
import aventurasdemarcoyluis.enemies.AbstractEnemy;
import aventurasdemarcoyluis.items.ItemType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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


    // Do Atacks
    public void hammerAttack(AbstractEnemy enemy){
        int cost = 2;
        int targetFP = this.getFp()-cost;

        if (this.isKO()){
            System.out.println(this.getName() + " is K.O. and can't attack");
            return;
        }

        if (targetFP<0){
            System.out.println("Not enough FP!");
            return;
        }

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

    public void jumpAttack(AbstractEnemy enemy){
        enemy.playerJumpAttacking(this);
    }

    // Receive Attacks
    public void enemyAttacking(AbstractEnemy enemy) {
        this.receiveDamage(this.computeDmg(0.75, enemy));
    }


    public void restoreFP(int fpToRestore){
        int targetFP = this.getFp() + fpToRestore;
        this.setFp(targetFP);
    }

    // Items
    public void addAnItem(AbstractItem a){
        armamento.add(a);
    }

    public void useItem(ItemType itemType){
        // Check if the player has an item of the kind we want to use in their armamento
        for(AbstractItem armamentoItem : armamento){
            if (armamentoItem.getType() == itemType) {
                armamentoItem.useItem(this);
                // We rwmove the item when used
                armamento.remove(armamentoItem);
                return;
            }
        }
        System.out.println(this.getName() + " doesn't have a/an " + itemType + " in their inventory!");
;
    }

    // Getters and setters
    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public boolean isInvincible() {
        return isInvincible;
    }
}



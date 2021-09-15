package aventurasdemarcoyluis;

import java.util.List;
import java.util.Random;


public abstract class AbstractPlayer extends AbstractEntity{


    protected boolean isInvincible=false;
    public List<ItemType> armamento;

    /**
     * Creates a new AbstractPlayer
     *  @param ATK  jumpAttack points
     * @param DEF  defense points
     * @param HP   heal points
     * @param TYPE type of the enemy (see enum "EnemyType")
     * @param NAME name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     */
    public AbstractPlayer(double ATK, double DEF, double FP, double HP, double MAXHP,int LVL, EntityType TYPE, String NAME) {
        super(ATK, DEF, FP, HP, MAXHP, LVL, TYPE, NAME);
    }


    public void addAnItem(ItemType a){
            armamento.add(a);
        }


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

    public void restoreFP(int fpToRestore){
        this.setFp(this.getFp()+fpToRestore);
    }
//
//    public void useItem(AbstractItem item){
//
//    }

}

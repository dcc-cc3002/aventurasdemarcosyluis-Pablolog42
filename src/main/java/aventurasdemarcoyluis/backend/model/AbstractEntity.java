package aventurasdemarcoyluis.backend.model;

import aventurasdemarcoyluis.backend.controller.GameController;
import org.jetbrains.annotations.NotNull;

/*
    Abstract representation of an entity in the game.
    An entity can be either a player, or an enemy.
 */
public abstract class AbstractEntity implements InterEntity {

    private GameController controller;
    private double atk;
    private double def;
    private double hp;
    private double maxHP;
    private int lvl;
    private boolean isKO;
    private final EntityType type;


    /**
     * Creates a new AbstractEnemy
     *
     * @param ATK   jumpAttack points
     * @param DEF   defense points
     * @param HP    health points
     * @param MAXHP Maximum HP of the unit
     * @param LVL   level of the Unit
     * @param TYPE  type of the enemy (see enum "EnemyType")
     */
    public AbstractEntity(double ATK, double DEF, double HP, double MAXHP, int LVL, EntityType TYPE) {
        this.atk = ATK;
        def = DEF;
        hp = HP;
        maxHP = MAXHP;
        lvl = LVL;
        type = TYPE;
        // By default, every entity is not KO
        isKO = false;

    }




    /**
     * Infringes a certain amount of damage to an entity
     * In case the damage inflicted renders the unit K.O. (targetHp <= 0),
     * sets the isKO field to true. and the character's HP to 0.
     *
     * @param damage Damage to inflict to the entity
     */
    @Override
    public void receiveDamage(double damage) {
        double targetHp = this.hp - damage;
        if (this.isKO) {
            System.out.println(this.type.toString() + " can't receive damage, they're K.O.!");
            return;
        }
        if (targetHp <= 0) {
            System.out.println(this.type.toString() + " has no HP left, they're now K.O!");
            // a dead enemy has 0 HP
            this.hp = 0;
            this.isKO = true;
            return;
        }
        System.out.println(this.type.toString() + " has received " + damage + " damage points!");
        this.hp = targetHp;
    }

    /**
     * Restores an amount of HP to an entity
     * In case the entity is K.O., resurrects the entity (sets isKO = false)
     * In case the targetHp exceeds the entity's maxHP, sets hp = maxHP
     *
     * @param hpToRestore The amount of HP to restore to the entity.
     */
    @Override
    public void restoreHP(double hpToRestore) {
        double targetHP = this.hp + hpToRestore;

        if (this.maxHP < targetHP) {
            this.setHp(maxHP);
            return;
        }
        this.setHp(targetHP);
    }


    /**
     * Computes the amount of damage to be inflicted (according to the dmg formula)
     * when receiving an attack from another AbstractEntity.
     *
     * @param k        The Base damage multiplier for a given attack
     * @param attacker The AbstractEntity currently attacking
     */
    @Override
    public double computeDmg(double k, @NotNull InterEntity attacker) {
        return (k * attacker.getAtk() * attacker.getLvl()) / this.getDef();
    }







    // Setters and getters

    /**
     * Gets the attack of an entity
     **/
    @Override
    public double getAtk() {
        return atk;
    }

    /**
     * Gets the level of an entity
     **/
    @Override
    public int getLvl() {
        return lvl;
    }

    /**
     * Gets the defense of an entity
     **/
    @Override
    public double getDef() {
        return def;
    }

    /**
     * Gets the current HP of an entity
     **/
    @Override
    public double getHp() {
        return hp;
    }

    /**
     * Gets the maximum HP of an entity
     **/
    @Override
    public double getMaxHP() {
        return maxHP;
    }

    /**
     * Gets the type of entity
     **/
    @Override
    public EntityType getType() {
        return type;
    }

    /**
     * Gets the Name of an entity
     **/
    @Override
    public String getName() {
        return this.type.toString();
    }

    /**
     * Gets the KO status of an entity
     **/
    @Override
    public boolean isKO() {
        return this.isKO;
    }

    /**
     * Sets the current HP of an entity
     **/
    @Override
    public void setHp(double hp) {
        this.hp = hp;
    }

    /**
     * Sets the KO status of an entity
     **/
    @Override
    public void setKO(boolean KO) {
        isKO = KO;
    }

    /**
     * Sets the atk of an entity
     **/
    public void setAtk(double atk) {
        this.atk = atk;
    }

    /**
     * Sets the def of an entity
     **/
    public void setDef(double def) {
        this.def = def;
    }

    /**
     * Sets the maxHP of an entity
     **/
    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    /**
     * Sets the lvl of an entity
     **/
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }



}

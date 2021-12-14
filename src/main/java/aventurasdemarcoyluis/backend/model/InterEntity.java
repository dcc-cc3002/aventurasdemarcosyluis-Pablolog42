package aventurasdemarcoyluis.backend.model;

import aventurasdemarcoyluis.backend.controller.GameController;

//TODO documentar
public interface InterEntity {
    /**
     * Infringes a certain amount of damage to an entity
     * In case the damage inflicted renders the unit K.O. (targetHp <= 0),
     * sets the isKO field to true. and the character's HP to 0.
     *
     * @param damage Damage to inflict to the entity
     */
    void receiveDamage(double damage);

    /**
     * Restores an amount of HP to an entity
     * In case the entity is K.O., resurrects the entity (sets isKO = false)
     * In case the targetHp exceeds the entity's maxHP, sets hp = maxHP
     *
     * @param hpToRestore The amount of HP to restore to the entity.
     */
    void restoreHP(double hpToRestore);

    /**
     * Provides a string representation of the current entity.
     * Overrides "toString" method in Object Class.
     *
     * @return a string representation of the current entity.
     */
    String toString();

    /**
     * Computes the amount of damage to be inflicted (according to the dmg formula)
     * when receiving an attack from another AbstractEntity.
     *
     * @param k      The Base damage multiplier for a given attack
     * @param player The AbstractEntity currently attacking
     */
    double computeDmg(double k, InterEntity player);



    /**
     * Gets the attack of an entity
     **/
    double getAtk();

    /**
     * Gets the lvl of an entity
     **/
    int getLvl();

    /**
     * Gets the MaxHP of an entity
     **/
    double getMaxHP();

    /**
     * Gets the type of entity as a string.
     **/
    String getName();

    /**
     * Gets the KO status of an entity
     **/
    boolean isKO();

    /**
     * Gets the EntityType of an entity
     **/
    EntityType getType();

    /**
     * Gets the def of an entity
     **/
    double getDef();

    /**
     * Gets the hp of an entity
     **/
    double getHp();

    /**
     * Directly Sets the hp of an entity.
     * @param hpToBeSet the hp to be set.
     */
    void setHp(double hpToBeSet);

    /**
     * Directly sets the KO status of an entitiy
     * @param KO the status to be set (indicated as a boolean)
     */
    void setKO(boolean KO);
}

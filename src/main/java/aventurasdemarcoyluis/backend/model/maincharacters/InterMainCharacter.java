package aventurasdemarcoyluis.backend.model.maincharacters;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.InterEntity;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.items.InterItem;


public interface InterMainCharacter extends InterEntity {
    /**
     * Receives the DD call of an enemy attacking.
     *
     * @param enemy The enemy Attacking
     */
    void enemyAttacking(InterEnemy enemy);

    /**
     * Restores a certain amount of FP to a MainCharacter.
     *
     * @param fpToRestore the FP to restore.
     */
    void restoreFP(int fpToRestore);

    /**
     * Uses a given item.
     *
     * @param item the item to use.
     */
    void useItem(InterItem item);


    //** Gets the mainCharacter's fp **//
    int getFp();

    //** Gets the mainCharacter's maxFp **//
    int getMaxFP();


    //TODO: comentar
    void validateAttack(AttackType attackType) throws InvalidAttackException;
}

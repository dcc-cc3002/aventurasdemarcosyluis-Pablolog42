package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.EnemyList;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import org.jetbrains.annotations.NotNull;

/**
 * interface depicting the methods used in an AttackTurn.
 * Extends InterTurn Interface
 */
public interface InterAttackTurn extends InterTurn{

    /**
     * Returns the enemy to attack, given the number of enemy selected from the list.
     *
     * @param enemyNumber The string that depicts the enemy number selected from the enemyList.
     * @return The Enemy associated to the entered EnemyNumber
     */
    InterEnemy retrieveEnemyToAttack(int enemyNumber) throws InvalidSelectionException;

    void attackSelectedEnemy(@NotNull AttackType attackSelection, InterEnemy attackedEnemy) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException;


    void setAttackType(AttackType attackType);

    void setEnemyNumberToAttack(int enemyNumberToAttack);
}

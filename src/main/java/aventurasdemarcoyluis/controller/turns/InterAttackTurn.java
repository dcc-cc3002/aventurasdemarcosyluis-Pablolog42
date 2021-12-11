package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.EnemyList;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface InterAttackTurn extends InterTurn{

    /**
     * Returns the enemy to attack, given the number of enemy selected from the list.
     *
     * @param enemyNumber The string that depicts the enemy number selected from the enemyList.
     * @return The Enemy associated to the entered EnemyNumber
     */
    InterEnemy retrieveEnemyToAttack(int enemyNumber) throws InvalidSelectionException;

    void attackSelectedEnemy(@NotNull AttackType attackSelection, InterEnemy attackedEnemy) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException;

    EnemyList getEnemyList();

    void setAttackType(AttackType attackType);

    void setEnemyNumberToAttack(int enemyNumberToAttack);
}

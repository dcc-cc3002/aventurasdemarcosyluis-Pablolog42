package aventurasdemarcoyluis.entities.maincharacters;

import aventurasdemarcoyluis.entities.InterEntity;
import aventurasdemarcoyluis.entities.enemies.InterEnemy;

public interface InterMainCharacter extends InterEntity {
    void enemyAttacking(InterEnemy enemy);
    boolean validateAttack(int fpCost);
    String getName();
}

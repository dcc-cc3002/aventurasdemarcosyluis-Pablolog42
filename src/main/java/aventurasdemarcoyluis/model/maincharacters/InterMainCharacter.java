package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.enemies.InterEnemy;

//TODO: Comentar la interface
public interface InterMainCharacter extends InterEntity {
    void enemyAttacking(InterEnemy enemy);
    void restoreFP(int fpToRestore);
    }

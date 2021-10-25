package aventurasdemarcoyluis.entities.maincharacters;

import aventurasdemarcoyluis.entities.InterEntity;
import aventurasdemarcoyluis.entities.enemies.InterEnemy;

//TODO: Comentar la interface
public interface InterMainCharacter extends InterEntity {
    void enemyAttacking(InterEnemy enemy);
    }

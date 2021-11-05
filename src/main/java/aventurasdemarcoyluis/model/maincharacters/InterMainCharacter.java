package aventurasdemarcoyluis.model.maincharacters;

import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.InterItem;

//TODO: Comentar la interface
public interface InterMainCharacter extends InterEntity {
    void enemyAttacking(InterEnemy enemy);
    void restoreFP(int fpToRestore);
    void useItem(InterItem item);
    }

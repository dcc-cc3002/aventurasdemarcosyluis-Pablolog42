package aventurasdemarcoyluis.entities.enemies;

import aventurasdemarcoyluis.entities.InterEntity;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;

public interface InterEnemy extends InterEntity {
    void attack(InterMainCharacter player);

    void playerHammerAttacking(InterMainCharacter character);

    void playerJumpAttacking(InterMainCharacter character);
}

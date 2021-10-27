package aventurasdemarcoyluis.entities.enemies;

import aventurasdemarcoyluis.entities.InterEntity;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;

//TODO: Comentar la interface
public interface InterEnemy extends InterEntity {

    void attack(InterMainCharacter player);

    void playerHammerAttacking(InterMainCharacter character);

    void playerJumpAttacking(InterMainCharacter character);

}

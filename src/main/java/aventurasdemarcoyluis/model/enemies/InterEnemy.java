package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

//TODO: Comentar la interface
public interface InterEnemy extends InterEntity {

    void attack(InterMainCharacter player);

    void playerHammerAttacking(InterMainCharacter character);

    void playerJumpAttacking(InterMainCharacter character);



}

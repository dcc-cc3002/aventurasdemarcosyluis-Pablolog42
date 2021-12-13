package aventurasdemarcoyluis.backend.model.enemies;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.maincharacters.InterLuis;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

public interface InterBoo {

    /**
     * As boo can't attack Marco, we segregate the attacks by the use of the "InterLuis" interface.
     * @param luis The Luis Main Character to be attacked.
     */
    void attack(InterMainCharacter mainCharacter) throws InvalidAttackException;


}

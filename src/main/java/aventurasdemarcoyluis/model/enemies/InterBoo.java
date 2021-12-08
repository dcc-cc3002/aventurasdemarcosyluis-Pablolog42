package aventurasdemarcoyluis.model.enemies;

import aventurasdemarcoyluis.model.maincharacters.InterLuis;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public interface InterBoo {

    /**
     * As boo can't attack Marco, we segregate the attacks by the use of the "InterLuis" interface.
     * @param luis The Luis Main Character to be attacked.
     */
    void attack(InterLuis luis);


}

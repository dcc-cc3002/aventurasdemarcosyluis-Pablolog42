package aventurasdemarcoyluis.entities.items;

import aventurasdemarcoyluis.Player;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;

/*
    HoneySyrup item Class
 */
public class HoneySyrup extends AbstractItem implements InterItem {
    public HoneySyrup() {
        super(ItemType.HONEYSYRUP);
    }

    /**
     * Uses the HoneySyrup item.
     * Restores 3 FP to the player
     *
     * @param character The player to use the item.
     **/
    @Override
    public void useItem(InterMainCharacter character) {
        character.restoreFP(3);
    }
}
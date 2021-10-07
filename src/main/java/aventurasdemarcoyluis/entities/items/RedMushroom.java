package aventurasdemarcoyluis.entities.items;

import aventurasdemarcoyluis.Player;
import aventurasdemarcoyluis.entities.EntityType;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;

/*
    RedMushroom item Class
 */
public class RedMushroom extends AbstractItem implements InterItem {
    public RedMushroom() {
        super(ItemType.REDMUSHROOM);
    }

    /**
     * Uses the Red Mushroom item.
     * Restores 10% of the player's maxHP to the player's current health.
     *
     * @param character the character who gets the item (either Luis or Marco)
     **/
    @Override
    public void useItem(AbstractMainCharacter character) {
        character.restoreHP(0.1 * character.getMaxHP());
    }
}

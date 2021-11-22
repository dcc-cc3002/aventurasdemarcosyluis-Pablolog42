package aventurasdemarcoyluis.model.items;

import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

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
    public void useItem(@NotNull InterMainCharacter character) {
        character.restoreHP(0.1 * character.getMaxHP());
    }

    /**
     * Get the type of  item
     *
     * @return The item's itemType
     */
    @Override
    public ItemType getType() {
        return ItemType.REDMUSHROOM;
    }
}

package aventurasdemarcoyluis.model.items;

import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

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
    public void useItem(@NotNull InterMainCharacter character) {
        character.restoreFP(3);
    }

    /**
     * Get the type of  item
     * @return The item's itemType
     */
    @Override
    public ItemType getType() {
        return ItemType.HONEYSYRUP;
    }
}
package aventurasdemarcoyluis.model.items;

import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public interface InterItem {
    /**
     * Let a mainCharacter use a particular item.
     * @param character The character to use the item.
     */
    void useItem(InterMainCharacter character);

    /**
     * Get the type of  item
     * @return The item's itemType
     */
    ItemType getType();
}

package aventurasdemarcoyluis.entities.items;

import aventurasdemarcoyluis.Player;

/*
    Abstract representation of an item in the game.
 */
public abstract class AbstractItem implements InterItem {

    private ItemType itemType;

    public AbstractItem(ItemType ITEMTYPE){
        itemType=ITEMTYPE;
    }

    /** Gets the type of item**/
    public ItemType getType(){
        return itemType;
    }
}

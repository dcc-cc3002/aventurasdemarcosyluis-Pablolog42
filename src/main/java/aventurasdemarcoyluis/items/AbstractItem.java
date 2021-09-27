package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.items.ItemType;
import aventurasdemarcoyluis.players.AbstractPlayer;

/*
    Abstract representation of an item in the game.
 */
public abstract class AbstractItem {

    private ItemType itemType;
    protected String itemName;

    public AbstractItem(ItemType ITEMTYPE, String ITEMNAME){
        itemName=ITEMNAME;
        itemType=ITEMTYPE;
    }

    // Only players can use items
    /**
     * Uses the item.
     *
     * @param player The player to use the item.
     **/
    public void useItem(AbstractPlayer player){}

    /** Gets the type of item**/
    public ItemType getType(){
        return itemType;
    }
}

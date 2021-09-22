package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.items.ItemType;
import aventurasdemarcoyluis.players.AbstractPlayer;

public abstract class AbstractItem {

    private ItemType itemType;
    protected String itemName;

    public AbstractItem(ItemType ITEMTYPE, String ITEMNAME){
        itemName=ITEMNAME;
        itemType=ITEMTYPE;
    }

    // TODO COMENTAR ESTO
    // Only players can use items
    public void useItem(AbstractPlayer player){}

    // TODO COMENTAR ESTO
    public ItemType getType(){
        return itemType;
    }
}

package aventurasdemarcoyluis.entities.items;

/*
    Abstract representation of an item in the game.
 */
public abstract class AbstractItem implements InterItem {

    private ItemType itemType;

    public AbstractItem(ItemType ITEMTYPE){
        itemType=ITEMTYPE;
    }

}

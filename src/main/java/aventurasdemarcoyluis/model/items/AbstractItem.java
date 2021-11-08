package aventurasdemarcoyluis.model.items;

/*
    Abstract representation of an item in the game.
 */
public abstract class AbstractItem implements InterItem {

    private ItemType itemType;

    /**
     * AbstractItem class constructor.
     * @param ITEMTYPE the type of item to create.
     */
    public AbstractItem(ItemType ITEMTYPE){
        itemType=ITEMTYPE;
    }

}

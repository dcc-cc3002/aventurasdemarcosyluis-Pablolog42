package aventurasdemarcoyluis;

public abstract class AbstractItem {

    private ItemType itemType;
    protected String itemName;

    public AbstractItem(ItemType ITEMTYPE, String ITEMNAME){
        itemName=ITEMNAME;
        itemType=ITEMTYPE;
    }

    // Only players can use items
    public void useItem(AbstractPlayer player){}
}

package aventurasdemarcoyluis.entities.items;

public class ItemVault {
    private int honeyAmount;
    private int mushroomAmount;

    public ItemVault() {
        this.honeyAmount = 0;
        this.mushroomAmount = 0;
    }

    public void modifyItemAmount(int amountToAddOrRemove, ItemType itemType) {
        switch (itemType) {
            case HONEYSYRUP -> this.honeyAmount = Math.max(amountToAddOrRemove + this.honeyAmount, 0);
            case REDMUSHROOM -> this.mushroomAmount = Math.max(amountToAddOrRemove + this.mushroomAmount, 0);
        }
    }

    public int getItemAmount(ItemType itemType) {
        switch (itemType) {
            case HONEYSYRUP -> {
                return this.honeyAmount;
            }
            case REDMUSHROOM -> {
                return this.mushroomAmount;
            }
        }
        return 0;
    }

    public AbstractItem retrieveItem(ItemType itemType){
        // Check if the player has an item of the kind we want to use in their armamento
        if(this.getItemAmount(itemType)<1){
            System.out.println("The player doesn't have a/an " + itemType.toString() + " in their inventory!");
            return null;
        }
        // Remove the used item from the inventory
        this.modifyItemAmount(-1,itemType);

        switch (itemType) {
            case HONEYSYRUP -> {return new HoneySyrup();}
            case REDMUSHROOM -> {return new RedMushroom();}
        }
        return null;
    }
}

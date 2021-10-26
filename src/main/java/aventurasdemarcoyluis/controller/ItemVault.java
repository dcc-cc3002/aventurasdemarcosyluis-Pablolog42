package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.entities.items.AbstractItem;
import aventurasdemarcoyluis.entities.items.HoneySyrup;
import aventurasdemarcoyluis.entities.items.ItemType;
import aventurasdemarcoyluis.entities.items.RedMushroom;
import org.jetbrains.annotations.NotNull;

// TODO: DOCUMENTARY ITEMVAULT
public class ItemVault {
    private int honeyAmount;
    private int mushroomAmount;

    public ItemVault() {
        this.honeyAmount = 0;
        this.mushroomAmount = 0;
    }

    public void modifyItemAmount(int amountToAddOrRemove, @NotNull ItemType itemType) {
        switch (itemType) {
            case HONEYSYRUP -> this.honeyAmount = Math.max(amountToAddOrRemove + this.honeyAmount, 0);
            case REDMUSHROOM -> this.mushroomAmount = Math.max(amountToAddOrRemove + this.mushroomAmount, 0);
        }
    }

    public int getItemAmount(@NotNull ItemType itemType) {
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
            System.out.println("The player doesn't have a/an " + itemType + " in their inventory!");
            return null;
        }
        // Remove the used item from the inventory
        this.modifyItemAmount(-1,itemType);

        switch (itemType) {
            case HONEYSYRUP -> {return new HoneySyrup();}
            case REDMUSHROOM -> {return new RedMushroom();}
        }
        // TODO: Add exception? no sé si acá o en los métodos que llaman a esto
        return null;
    }


    // Overriding equals() to compare two Complex objects
    // Code Referenced from https://www.geeksforgeeks.org/overriding-equals-method-in-java/
    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of ItemVault or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof ItemVault)) {
            return false;
        }

        // Compare the data members and return accordingly
        return (((ItemVault) o).getItemAmount(ItemType.REDMUSHROOM) == this.mushroomAmount) && (((ItemVault) o).getItemAmount(ItemType.HONEYSYRUP) == this.honeyAmount);
    }
}



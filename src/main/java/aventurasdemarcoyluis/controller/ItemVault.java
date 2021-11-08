package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.items.AbstractItem;
import aventurasdemarcoyluis.model.items.HoneySyrup;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.items.RedMushroom;
import org.jetbrains.annotations.NotNull;

/**
 * Class that depicts the representation of a vault of items.
 */
public class ItemVault {
    private int honeyAmount;
    private int mushroomAmount;

    /**
     * ItemVault Class constructor.
     * Every itemVault consists of a collection of two values indicating the amount of items of a certain kind.
     * every ItemVault starts with 0 items of each type (mushroom or honey).
     */
    public ItemVault() {
        this.honeyAmount = 0;
        this.mushroomAmount = 0;
    }

    /**
     * Modifies the amount of an item in an ItemVault.
     * If the amount to modify is negative, removes items from the ItemVault.
     * If removing an amount of items results in a negative balance, restores the balance to 0.
     * @param amountToAddOrRemove The amount of items to add or remove from the ItemVault.
     * @param itemType The kind of item to be removed from the ItemVault (either Red Mushroom or Honey Syrup).
     */
    public void modifyItemAmount(int amountToAddOrRemove, @NotNull ItemType itemType) {
        switch (itemType) {
            case HONEYSYRUP -> this.honeyAmount = Math.max(amountToAddOrRemove + this.honeyAmount, 0);
            case REDMUSHROOM -> this.mushroomAmount = Math.max(amountToAddOrRemove + this.mushroomAmount, 0);
        }
    }

    /**
     * Gets the amount of an item in the vault.
     *
     * @param itemType the kind of item to get the amount of
     * @return An integer indicating the amount of item left in the vault.
     */
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

    /**
     * Retrieves an item from the vault to be used.
     * Retrieving an item decreases the amount of that item by 1, and can only be done if there exist any of the
     * item to use left in the vault.
     * @param itemType The type of item to be retrieved
     * @return An instance of the item retrieved from the vault
     */
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

    /**
    Overriding equals() to compare two Complex objects
    Code Referenced from https://www.geeksforgeeks.org/overriding-equals-method-in-java/
     For two vaults to be considered equal, the amounts and types of the vaults have to be the same.

     @return Returns the boolean indicating if two vaults are the same.
    */
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

    /**
     * Overrides the toString() method in Object class.
     * Returns a screen with the information characterising the given vault.
     *
     * @return A string containing the current item amounts in a vault.
     */
    @Override
    public String toString(){
        return "++++++++++ Player  Vault ++++++++++\n" +
                "Honey Syrup amount: " + this.honeyAmount + "\n" +
                "Red Mushroom amount: " + this.mushroomAmount + "\n" +
                "++++++++++++++++++++++++++++++++++";
    }

    /**
     * Returns a boolean that indicates if the vault is empty.
     * A vault is considered empty when both item amounts are 0.
     * @return a boolean that indicates if the vault is empty.
     */
    public boolean isEmpty() {
        return (this.mushroomAmount == 0)&&(this.honeyAmount==0);
    }
}



package aventurasdemarcoyluis;

import aventurasdemarcoyluis.entities.items.AbstractItem;
import aventurasdemarcoyluis.entities.items.ItemType;
import aventurasdemarcoyluis.entities.items.ItemVault;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.entities.maincharacters.Luis;
import aventurasdemarcoyluis.entities.maincharacters.Marco;

import java.util.List;

/*
 * Representation of a real-life player.
 * Every Player has a chest with their items.
 */
public class Player {

    private String playerName;
    private ItemVault playerVault;
    private Marco playerMarco;
    private Luis playerLuis;

    public Player(String name, Marco marco, Luis luis){
        this.playerLuis = luis;
        this.playerMarco = marco;
        this.playerName = name;
        playerVault = new ItemVault();
    }

    // Items

    /**
     * Adds an item to a Players' armament.
     * @param itemType the type of the item to add to the players' inventory.
     */
    public void addAnItem(ItemType itemType){
        playerVault.modifyItemAmount(1,itemType);
    }

    /**
     * Sends the message to use an item from a Players' armament.
     * Checks if the player has an item of a certain kind in their armament.
     * in case they do, consumes the item (removes it from the Players' inventory) and uses it.
     * @param itemType the type of Item to use.
     *
     */
    public void useItem(ItemType itemType, AbstractMainCharacter character){

        if(playerVault.getItemAmount(itemType)<1){
            System.out.println(this.playerName + " doesn't have a/an " + itemType.toString() + " in their inventory!");
            return;
        }

        AbstractItem instancedItem = playerVault.retrieveItem(itemType);


        character.useItem(instancedItem);

    }


    // Getters and setters //

    /* Gets the Players' Armament.*/
    public ItemVault getPlayerVault() {
        return playerVault;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Marco getMarco() {
        return playerMarco;
    }

    public Luis getLuis() {
        return playerLuis;
    }
}

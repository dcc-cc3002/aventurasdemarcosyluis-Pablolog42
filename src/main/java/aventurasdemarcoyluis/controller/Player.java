package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.model.items.AbstractItem;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;

/*
 * Representation of a real-life player.
 * Every Player has a chest with their items.
 */
public class Player {

    private String playerName;
    private ItemVault playerVault;
    private EnemyList enemyList;
    private Marco playerMarco;
    private Luis playerLuis;
    private int battleNumber;

    public Player(String name, Marco marco, Luis luis){
        this.playerLuis = luis;
        this.playerMarco = marco;
        this.playerName = name;
        this.enemyList = new EnemyList();
        this.playerVault = new ItemVault();
        this.battleNumber = 0;
    }
    

    // By default, the values for Marco, Luis are: ATK 10, DEF 10, FP 10, MAXFP 100, HP 10, MAXHP 100, LVL 1
    public Player(String name){
        this.playerLuis = new Luis(10,10,10,100,10,100,1);
        this.playerMarco = new Marco(10,10,10,100,10,100,1);
        this.playerName = name;
        this.playerVault = new ItemVault();
        this.enemyList = new EnemyList();
    }



    // K.O.
    /**
     * Verifies if a player is K.O.
     * note: a player is K.O. when both Marco and Luis are K.O.
     * @return A Boolean indicating the KO state of the player.
     */
    public Boolean isPlayerKO(){
        return (this.getLuis().isKO() && this.getMarco().isKO());
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
     * Adds an item to a Players' armament.
     * @param itemType the type of the item to add to the players' inventory.
     * @param amount The amount of items to add.
     */
    public void addAnItem(ItemType itemType, int amount){
        playerVault.modifyItemAmount(amount,itemType);
    }




    /**
     * Sends the message to use an item from a Players' armament.
     * Checks if the player has an item of a certain kind in their armament.
     * in case they do, consumes the item (removes it from the Players' inventory) and uses it.
     * @param itemType the type of Item to use.
     *
     */
    public void useItem(ItemType itemType, AbstractMainCharacter character){
        // Case no item to use
        if(playerVault.getItemAmount(itemType)<1){
            System.out.println(this.playerName + " doesn't have a/an " + itemType.toString() + " in their inventory!");
            return;
        }
        // Case the character to use the item is KO
        if(character.isKO()){
            System.out.println(character.getName() + " can't use an item, as they are K.O!");
            return;
        }
        AbstractItem instancedItem = playerVault.retrieveItem(itemType);
        character.useItem(instancedItem);
    }

    public void increaseBattleNumber() {
        this.battleNumber = this.battleNumber + 1;
    }


    // Getters and setters //

    /* Gets the Players' Armament.*/
    public ItemVault getPlayerVault() {
        return playerVault;
    }

    public EnemyList getEnemyList() {
        return this.enemyList;
    }

    public Marco getMarco() {
        return playerMarco;
    }

    public Luis getLuis() {
        return playerLuis;
    }

    public int getBattleNumber() {
        return battleNumber;
    }

    public String getPlayerName() {
        return playerName;
    }


}

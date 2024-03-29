package aventurasdemarcoyluis.backend.model;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.model.items.AbstractItem;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import aventurasdemarcoyluis.backend.model.items.ItemVault;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;


/**
 * Representation of a real-life player.
 * Every Player has a chest with their items.
 */
public class Player {

    private final String playerName;
    private final ItemVault playerVault;

    private final GameController controller;

    private final Marco playerMarco;
    private final Luis playerLuis;

    private int battleNumber;
    private int playerLvl;

    /**
     * Player class constructor, without any specific mainCharacters given.
     * By default, initializes the values for Marco, Luis as: ATK 10, DEF 10, FP 10, MAXFP 20, HP 10, MAXHP 20, LVL 1
     *
     * @param name The name of the player to be created.
     * @param controller the controller handling the game.
     */
    public Player(String name, GameController controller) {
        this.controller = controller;
        this.playerLuis = (Luis) controller.createMainCharacter(EntityType.LUIS,10,10,10,20,10,20,1);
        this.playerMarco = (Marco) controller.createMainCharacter(EntityType.MARCO,10,10,10,20,10,20,1);
        this.playerLvl = 1;

        this.playerName = name;
        this.playerVault = new ItemVault();
        this.battleNumber=0;
     }


    // K.O.

    /**
     * Verifies if a player is K.O.
     * note: a player is K.O. when both Marco and Luis are K.O.
     *
     * @return A Boolean indicating the KO state of the player.
     */
    public Boolean isPlayerKO() {
        return (this.getLuis().isKO() && this.getMarco().isKO());
    }


    // Items

    /**
     * Adds an item to a Players' armament.
     *
     * @param itemType the type of the item to add to the players' inventory.
     */
    public void addAnItem(ItemType itemType) {
        playerVault.modifyItemAmount(1, itemType);
    }

    /**
     * Adds an item to a Players' armament.
     *
     * @param itemType the type of the item to add to the players' inventory.
     * @param amount   The amount of items to add.
     */
    public void addAnItem(ItemType itemType, int amount) {
        playerVault.modifyItemAmount(amount, itemType);
    }

    /**
     * Sends the message to use an item from a Players' armament.
     * Checks if the player has an item of a certain kind in their armament.
     * in case they do, consumes the item (removes it from the Players' inventory) and uses it.
     *
     * @param itemType the type of Item to use.
     */
    public void tryToUseItem(ItemType itemType, InterMainCharacter character) throws InvalidSelectionException {
        // Case no item to use
        if (playerVault.getItemAmount(itemType) < 1) {
            throw new InvalidSelectionException(this.playerName + " doesn't have a/an " + itemType + " in their inventory!");
        }
        // Case the character to use the item is KO
        if (character.isKO()) {
            throw new InvalidSelectionException(character.getName() + " can't use an item, as they are K.O!");
        }
        AbstractItem instancedItem = playerVault.retrieveItem(itemType);
        character.useItem(instancedItem);
    }

    /**
     * Increases the player's battleNumber by 1.
     */
    public void increaseBattleNumber() {
        this.battleNumber = this.battleNumber + 1;
    }

    /**
     * Runs the player level up routine.
     * Every level increase, the player receives one item of each kind.
     * Also, a level increase implies that the player's main characters increase level too.
     * <p>
     * Note: This method doesn't do anything when the player's level is 1. This is because there isn't a level increase yet
     * (The player starts at lvl=1).
     */
    public void lvlUp() {
        if (this.battleNumber == 0) {
            return;
        }
        this.addAnItem(ItemType.REDMUSHROOM);
        this.addAnItem(ItemType.HONEYSYRUP);
        this.getLuis().lvlUp();
        this.getMarco().lvlUp();

//        this.setPlayerLvl(getPlayerLvl()+1);

    }




    // Getters and setters //

    /**
     * Gets the Players' vault.
     **/
    public ItemVault getPlayerVault() {
        return playerVault;
    }





    /**
     * Gets the Players' Marco MainCharacter.
     **/
    public Marco getMarco() {
        return playerMarco;
    }

    /**
     * Gets the Players' Luis MainCharacter.
     **/
    public Luis getLuis() {
        return playerLuis;
    }

    /**
     * Gets the Players' current battle number. (Number of battles won by player)
     **/
    public int getBattleNumber() {
        return battleNumber;
    }

    /**
     * Gets the Players' Name.
     **/
    public String getPlayerName() {
        return playerName;
    }



    /**
     * Gets the Players' current level.
     **/
    public int getPlayerLvl() {
        return playerLvl;
    }

    /**
     * Sets the Players' current level.
     **/
    public void setPlayerLvl(int playerLvl) {
        this.playerLvl = playerLvl;

    }


}

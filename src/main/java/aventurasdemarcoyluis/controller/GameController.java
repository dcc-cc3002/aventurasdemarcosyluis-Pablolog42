package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.turns.*;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.*;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Main Class of the controller Module.
 * Handles all the controller logic, integrating Turns, Battles, Vaults and the player.
 */
public class GameController implements InterController {

    private String currentBattleWinner=null;
    private Battle currentBattle=null;
    private Player mainPlayer;
    private InterTurn currentTurn=null;
    private TurnOwner currentTurnOwner;
    private TurnOwner nextTurnOwner = null;
    private boolean winner;
    private boolean gameFinished;

    /**
     * GameController constructor.
     * Sets the current fields:
     *
     * By default, sets a new placeholder player with the name "J1"
     * Also, hands the initial turn to the player (setting the TURNOWNER variable).
     *
     * Sets the winner to false by default (enemy win until proven wrong).
     * Sets the gameFinished status to false.
     *
     */
    public GameController(){

        // The player always plays first
        this.currentTurnOwner = TurnOwner.PLAYER;
        this.mainPlayer = new Player("J1");

        this.winner = false;
        this.gameFinished = false;
       }


    /**
     * Lets the controller create a mainCharacter
     *
     * @param type The EntityType of the Main character to create.
     * @param atk  The atk of the unit to create.
     * @param def  The def of the unit to create.
     * @param hp The hp of the unit to create.
     * @param maxHP The maxHP of the unit to create.
     * @param fp The fp of the unit to create.
     * @param maxFP The maxFP of the unit to create.
     * @param lvl The level of the unit to create.
     * @return The InterMainCharacter created
     */
    @Override
    public InterMainCharacter createMainCharacter(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int fp, int maxFP, int lvl){
        switch (type){
            case MARCO -> { return new Marco(atk,def,fp,maxFP,hp,maxHP,lvl); }
            case LUIS -> { return new Luis(atk,def,fp,maxFP,hp,maxHP,lvl);}
        }
        return null;
    }

    /**
     * Lets the controller create an enemy.
     *
     * @param type The EntityType of the Main character to create.
     * @param atk  The atk of the unit to create.
     * @param def  The def of the unit to create.
     * @param hp The hp of the unit to create.
     * @param maxHP The maxHP of the unit to create.
     * @param lvl The level of the unit to create.
     * @return The InterMainCharacter created
     */
    @Override
    public AbstractEnemy createEnemy(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int lvl){
        switch (type){
            case GOOMBA -> { return new Goomba(atk,def,hp,maxHP,lvl); }
            case BOO -> { return new Boo(atk,def,hp,maxHP,lvl); }
            case SPINY -> { return new Spiny(atk,def,hp,maxHP,lvl); }
        }
        return null;
    }

    /**
     * Adds an amount of 1 item of a given type to the controler player's vault.
     * @param item The ItemType to add.
     */
    @Override
    public void addItemToPlayer(ItemType item){
        this.mainPlayer.getPlayerVault().modifyItemAmount(1,item);
    }

    /**
     * Runs the set-up routine for generating a new battle.
     * 1. Creates a battle instance
     * 2. increases the player's battle number
     * 3. Restores the player's fp and hp to max before the battle begins
     * 4. Generates the random enemy list to fight, in correspondence to the current Battlenumber.
     * 5. Adds the initial items (3 Honey and 3 mushroom) to the player in the player's first battle.
     * 6. Runs the player lvlUp routine, in case this isn't the first battle played.
     *
     */
    @Override
    public void createAndSetNewBattle() {
        this.currentBattle = new Battle(this.mainPlayer);

        this.mainPlayer.increaseBattleNumber();
        // Restore the player's character's fp and hp to the max
        this.mainPlayer.getMarco().restoreHP(mainPlayer.getMarco().getMaxHP());
        this.mainPlayer.getLuis().restoreHP(mainPlayer.getLuis().getMaxHP());
        this.mainPlayer.getMarco().restoreFP(mainPlayer.getMarco().getMaxFP());
        this.mainPlayer.getLuis().restoreFP(mainPlayer.getLuis().getMaxFP());

        this.currentBattle.setRandomEnemyList();
        this.currentBattle.addInitialItems();

        if (this.mainPlayer.getBattleNumber()>1) {
            this.getPlayer().setPlayerLvl(getPlayer().getPlayerLvl() +1 );
            this.playerLvlUp();
        }

    }

    /**
     * Sends the message to the controller's player to lvlUP.
     * Note: does nothing if the player's current lvl is 1. this is so we don't add more than 3 items on the
     * players first turn.
     */
    @Override
    public void playerLvlUp(){
        if(this.getPlayer().getPlayerLvl() == 1){
            //do nothing
            return;
        }
        this.mainPlayer.lvlUp();
    }

    /**
     * Indicates to the controller which type of turn will be performed in the current turn.
     * Instances a given turnType accordingly, and sets the controllers' current turn to the selected turn.
     *
     * @param selection a string denoting the type of turn to be executed.
     *                  "attack": creates an attackTurn
     *                  "item": creates an ItemTurn
     *                  "passing": creates a passingTurn
     *                  any other: doesn't do anything.
     */
    @Override
    public void selectTurnKind(@NotNull String selection) {
        // This switch statement sets the current turn
        switch (selection) {
                case "attack" -> {
                    InterTurn attackTurn = new AttackTurn(this);
                    this.setCurrentTurn(attackTurn);
                }
                case "item" -> {
                    // In case the player wants to use an item, but has none left.
                    if (this.mainPlayer.getPlayerVault().isEmpty()) {
                        System.out.println("You can't use an item, as you have none left!");
                        // Note: in the "flow" version (tarea 3), change this break for a "continue"
                        break;
                    }

                    ItemTurn itemTurn = new ItemTurn(this);
                    this.setCurrentTurn(itemTurn);

                }
                case "passing" -> {
                    InterTurn passingTurn = new PassingTurn(this);
                    this.setCurrentTurn(passingTurn);
                }
                default -> System.out.println("Please, select a valid option");
        }
    }

    /**
     * Executes the main method of the controller's currently selected turn.
     * @throws IOException Exception related to an unexpected input received by the ReadableBuffer.
     */
    @Override
    public void startCurrentTurn() throws IOException {
        this.currentTurn.main();
    }

    /**
     * Finishes the controller's player turn.
     *
     * Also, checks if the player has won or lost (according to the win or lose conditions)
     * In case the player has won, executes the playerWinning sequence.
     * Something analogous happens when the player has lost.
     *
     */
    @Override
    public void finishTurn(){

        // This handles the finish game logic.
        // check if the player is KO after the turn.
        if(this.getPlayer().isPlayerKO()){
            this.setGameFinished(true);
            this.playerLosingSequence();
            return;
        }
        if (this.getPlayer().getBattleNumber() >= 5){
            this.setGameFinished(true);
            this.playerWinningSequence();
            return;
        }

        if(this.getCurrentTurnOwner() == TurnOwner.PLAYER){
            this.setCurrentTurn(new EnemyTurn(this));
            this.setNextTurnOwner(TurnOwner.ENEMY);
        }
        if(this.getCurrentTurnOwner() == TurnOwner.ENEMY){
            this.setNextTurnOwner(TurnOwner.PLAYER);
        }

    }

    /**
     * Sequence to be executed when a player wins the game.
     * sets the controller's winner to true (Player)
     * Prints a congrats message to the screen.
     */
    @Override
    public void playerWinningSequence() {
        this.setWinner(true);
        System.out.println("Congrats, " + this.getPlayer().getPlayerName() + ". You have won the game!");
    }

    /**
     * Sequence to be executed when a player loses the game.
     * sets the controller's winner to false (Enemies win)
     * Prints a sad message to the screen.
     */
    @Override
    public void playerLosingSequence() {
        this.setWinner(false);
        System.out.println("Sorry, " + this.getPlayer().getPlayerName() + ". You have lost the game :(");

    }


    /* Getters and setters */

    /**
     * Gets one of the controller's Player main characters. (either marco or luis.)
     * @param type the EntityType of the character to retrieve.
     * @return the players main character, according to the Type given.
     */
    public InterMainCharacter getPlayerMainCharacter(EntityType type){
        return type == EntityType.MARCO? this.mainPlayer.getMarco():this.mainPlayer.getLuis();
    }

    /**
     * Gets the gameFinished status.
     * @return the boolean indicating if the whole game has finished.
     */
    public boolean isGameFinished(){return gameFinished;}

    /**
     * Sets the gameFinished status
     * @param gameFinished the boolean indicating if the game is finished.
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Gets the Owner of the next turn to be played.
     * The owner can be either the player, or the enemy.
     * @return The TurnOwner type of the next turn.
     */
    public TurnOwner getNextTurnOwner(){
        switch (this.getCurrentTurn().getType()){
            case ITEM,ATTACK,PASSING -> {return TurnOwner.ENEMY;}
            case ENEMY -> {return TurnOwner.PLAYER;}
        }
        return null;
    }

    /**
     * Sets the next turn owner
     * @param nextTurnOwner the TurnOwner type of the next turn.
     */
    public void setNextTurnOwner(TurnOwner nextTurnOwner) {
        this.nextTurnOwner = nextTurnOwner;
    }

    /**
     * gets the current turn owner.
     * @return TurnOwner describing the current turn owner.
     */
    public TurnOwner getCurrentTurnOwner() {
        return currentTurnOwner;
    }

    /**
     * Ges the game's winner.
     * @return A boolean denoting the games' winner.
     *         True -> Player win
     *         False -> Enemy Win
     */
    public boolean getWinner() {
        return winner;
    }

    /**
     * Sets the game's winner.
     * @param winner  A boolean denoting the games' winner.
     *         True -> Player win
     *         False -> Enemy Win
     */
    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    /**
     * Gets the controller's current played turn.
     * @return The current turn being played.
     */
    public InterTurn getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Sets (updates) the current turn.
     * @param currentTurn The InterTurn to update the current turn with.
     */
    public void setCurrentTurn(InterTurn currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Gets The controller's main player.
     * @return The controller's current main player.
     */
    public Player getPlayer() {
        return mainPlayer;
    }

    /**
     * Sets the controller's Player.
     * @param player the player to be set.
     */
    public void setPlayer(Player player) {
        this.mainPlayer = player;
    }

    /**
     * Creates a Player with a given name, and then sets the controller's player to be that player. .
     * @param playerName the name of the player to be created and set.
     */
    public void setPlayer(String playerName) {
        this.mainPlayer = new Player(playerName);
    }


}

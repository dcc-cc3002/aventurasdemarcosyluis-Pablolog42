package aventurasdemarcoyluis.controller;

import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTurnException;
import aventurasdemarcoyluis.controller.handlers.EntityKoHandler;
import aventurasdemarcoyluis.controller.phases.InterPhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.phases.StartBattlePhase;
import aventurasdemarcoyluis.controller.turns.*;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.enemies.*;
import aventurasdemarcoyluis.model.items.InterItem;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main Class of the controller Module.
 * Handles all the controller logic, integrating Turns, Battles, Vaults and the player.
 */
public class GameController implements InterController {

    private Player mainPlayer;
    private InterTurn currentTurn = null;

    private TurnOwner currentTurnOwner;
    private TurnOwner nextTurnOwner;

    private InterMainCharacter currentInvolvedMainCharacter = null;

    private Battle currentBattle = null;

    private final ArrayList<InterMainCharacter> mainCharacterList;
//    private final ArrayList<InterMainCharacter> activeMainCharacterList;


    private final ActiveMainCharacterList activeMainCharacterList;

    private final EnemyList enemyList;

    private boolean playerWon;
    private boolean gameFinished;

    private InterPhase phase;


    private final EntityKoHandler entityKoNotification = new EntityKoHandler(this);

    private PropertyChangeSupport playerHasWonBattle = new PropertyChangeSupport(this);


    /**
     * GameController constructor.
     * Sets the current fields:
     * <p>
     * By default, sets a new placeholder player with the name "J1"
     * Also, hands the initial turn to the player (setting the TURNOWNER variable).
     * <p>
     * Sets the winner to false by default (enemy win until proven wrong).
     * Sets the gameFinished status to false.
     */
    public GameController() {

        // The player's mario always plays first
        this.currentTurnOwner = TurnOwner.MARCO;
        // This should be luis
        this.nextTurnOwner = calculateNextTurnOwner();

        this.mainPlayer = new Player("J1");

        this.mainCharacterList = new ArrayList<>();

        this.activeMainCharacterList = new ActiveMainCharacterList(this);
        activeMainCharacterList.initialSetup();


        mainCharacterList.add(mainPlayer.getMarco());
        mainCharacterList.add(mainPlayer.getLuis());



        this.enemyList = new EnemyList();

        this.playerWon = false;
        this.gameFinished = false;



        this.phase = new StartBattlePhase(this);

    }


    /**
     * Lets the controller create a mainCharacter
     *
     * @param type  The EntityType of the Main character to create.
     * @param atk   The atk of the unit to create.
     * @param def   The def of the unit to create.
     * @param hp    The hp of the unit to create.
     * @param maxHP The maxHP of the unit to create.
     * @param fp    The fp of the unit to create.
     * @param maxFP The maxFP of the unit to create.
     * @param lvl   The level of the unit to create.
     * @return The InterMainCharacter created
     */
    @Override
    public InterMainCharacter createMainCharacter(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int fp, int maxFP, int lvl) {
        switch (type) {
            case MARCO -> {
                return new Marco(atk, def, fp, maxFP, hp, maxHP, lvl);
            }
            case LUIS -> {
                return new Luis(atk, def, fp, maxFP, hp, maxHP, lvl);
            }
        }
        return null;
    }

    /**
     * Lets the controller create an enemy.
     *
     * @param type  The EntityType of the Main character to create.
     * @param atk   The atk of the unit to create.
     * @param def   The def of the unit to create.
     * @param hp    The hp of the unit to create.
     * @param maxHP The maxHP of the unit to create.
     * @param lvl   The level of the unit to create.
     * @return The InterMainCharacter created
     */
    @Override
    public AbstractEnemy createEnemy(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int lvl) {
        switch (type) {
            case GOOMBA -> {
                return new Goomba(atk, def, hp, maxHP, lvl);
            }
            case BOO -> {
                return new Boo(atk, def, hp, maxHP, lvl);
            }
            case SPINY -> {
                return new Spiny(atk, def, hp, maxHP, lvl);
            }
        }
        return null;
    }

    /**
     * Adds an amount of 1 item of a given type to the controler player's vault.
     *
     * @param item The ItemType to add.
     */
    @Override
    public void addItemToPlayer(ItemType item) {
        this.mainPlayer.getPlayerVault().modifyItemAmount(1, item);
    }

    /**
     * Runs the set-up routine for generating a new battle.
     * 1. Creates a battle instance
     * 2. increases the player's battle number
     * 3. Restores the player's fp and hp to max before the battle begins
     * 4. Generates the random enemy list to fight, in correspondence to the current Battlenumber.
     * 5. Adds the initial items (3 Honey and 3 mushroom) to the player in the player's first battle.
     * 6. Runs the player lvlUp routine, in case this isn't the first battle played.
     */
    @Override
    public void createAndSetNewBattle() {

        Battle currentBattle = new Battle(this.mainPlayer,this);

        this.setCurrentBattle(currentBattle);

        this.mainPlayer.increaseBattleNumber();

        // The lvlUp routine changes the player's main character stats accordingly
        //  **Implementation Note** and "revives" a mainCharacter in case they got KO in the previous battle.
        if (this.mainPlayer.getBattleNumber() > 1) {
            this.playerLvlUp();
        }

        // After the levelup routine, the player's characters will be updated, but not the active main characters yet.
        this.activeMainCharacterList.restoreList(getPlayer().getMarco(),getPlayer().getLuis());


        currentBattle.setRandomEnemyList();
        currentBattle.addInitialItems();



    }

    /**
     * Sends the message to the controller's player to lvlUP.
     * Note: does nothing if the player's current lvl is 1. this is so we don't add more than 3 items on the
     * players first turn.
     */
    @Override
    public void playerLvlUp() {
        if (this.getPlayer().getPlayerLvl() == 1) {
            //do nothing
            return;
        }
        this.getPlayer().setPlayerLvl(getPlayer().getPlayerLvl() + 1);
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
    public void tryToSelectNewTurnKind(@NotNull TurnType selection) throws InvalidSelectionException {
        switch (selection) {
            case ATTACK -> {
                InterTurn attackTurn = new AttackTurn(this);
                this.setCurrentTurn(attackTurn);
            }
            case ITEM -> {
                // In case the player wants to use an item, but has none left.
                if (this.getPlayer().getPlayerVault().isEmpty()) {
                    throw new InvalidSelectionException("You can't use an item, as you have none left!");
                }
                if (this.currentInvolvedMainCharacter.isKO()){
                    throw new InvalidSelectionException("You can't use an item as the current character is KO.");
                }

                InterItemTurn itemTurn = new ItemTurn(this);
                this.setCurrentTurn(itemTurn);

            }
            case PASSING -> {
                PassingTurn passingTurn = new PassingTurn(this);
                this.setCurrentTurn(passingTurn);
            }
            default -> this.setCurrentTurn(null);
        }

    }

    /**
     * Executes the main method of the controller's currently selected turn.
     *
     * @throws IOException Exception related to an unexpected input received by the ReadableBuffer.
     */
    @Override
    public void startCurrentTurn() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {
        try {
            this.currentTurn.main();
        }catch (NullPointerException e){
            throw new InvalidTransitionException("You Haven't selected a turn Type!");
        }

    }

    /**
     * Finishes the controller's player turn.
     * <p>
     * Also, checks if the player has won or lost (according to the win or lose conditions)
     * In case the player has won, executes the playerWinning sequence.
     * Something analogous happens when the player has lost.
     */
    @Override
    public void finishTurn() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {

        // This handles the finish game logic.
        // check if the player is KO after the turn.
        // TODO: this should be handled by the isKO method in battle class
        if (this.getPlayer().isPlayerKO()) {
            this.setGameFinished(true);
            // TODO: This should be implemented by an observer.
            this.playerLosingSequence();
            return;
        }
        if (this.getPlayer().getBattleNumber() >= 5) {
            this.setGameFinished(true);
            this.playerWinningSequence();
            return;
        }

        // Si el turno que se está terminando es de Luis,
        //  el siguiente será del enemigo.
        if(this.getCurrentTurnOwner() == TurnOwner.LUIS){
            // Se setea el turno siguiente a que sea del enemigo.

            this.setCurrentTurnOwner(TurnOwner.ENEMY);
            this.setNextTurnOwner(calculateNextTurnOwner());

            this.setCurrentTurn(new EnemyTurn(this));

            // The enemies turn should be started immediately after the player's
            // last main character has finished their turn.
            this.startCurrentTurn();
            return;
        }


        assert (this.getNextTurnOwner() != TurnOwner.ENEMY);

        // As the next turn isn't of an Enemy, and the player hasn't selected wich
        // turn kind they want to perform next, we set it to null.
        this.setCurrentTurn(null);

        // Set's the next turn's owner according to what is specified
        this.setCurrentTurnOwner(getNextTurnOwner());
        // As the current turn owner has been updated, also update the new next turn owner
        this.setNextTurnOwner(calculateNextTurnOwner());


    }


    private void setInvolvedMainCharacter(InterMainCharacter playerMainCharacter) {

    }

    public InterMainCharacter getCurrentTurnMainCharacter(){
        TurnOwner turnOwner = getCurrentTurnOwner();

        switch (turnOwner){
            case LUIS -> {return getPlayerMainCharacter(EntityType.LUIS);}
            case MARCO -> {return getPlayerMainCharacter(EntityType.MARCO);}
        }
        return null;
    }

    public InterMainCharacter getRandomMainCharacterFromActiveList(){
        Random randomizer = new Random();
        return activeMainCharacterList.get(randomizer.nextInt(activeMainCharacterList.size()));
    }



    public void tryToChangePhase(Phase phaseToChangeTo) throws InvalidTransitionException {
        // If the phase transition is illegal
        if(!this.phase.validatePhaseChange(phaseToChangeTo)){
            throw new InvalidTransitionException("You can't Transition phase at this time!");
        }
        this.setPhase(phaseToChangeTo);
    }

    /**
     * Sequence to be executed when a player wins the game.
     * sets the controller's winner to true (Player)
     * Prints a congrats message to the screen.
     */
    @Override
    public void playerWinningSequence() {
        this.setPlayerWon(true);
        System.out.println("Congrats, " + this.getPlayer().getPlayerName() + ". You have won the game!");
    }

    /**
     * Sequence to be executed when a player loses the game.
     * sets the controller's winner to false (Enemies win)
     * Prints a sad message to the screen.
     */
    @Override
    public void playerLosingSequence() {
        this.setPlayerWon(false);
        System.out.println("Sorry, " + this.getPlayer().getPlayerName() + ". You have lost the game :(");

    }



    @Override
    public void setPhase(Phase phase) {
        this.phase = phase;
    }


    public void updateEntityKoStatus(InterEntity entity){

        switch (entity.getType()){
            case MARCO,LUIS -> removeKoMainCharactersFromList();
            case BOO,SPINY,GOOMBA -> removeKoEnemiesFromList();
        }

        if(enemyList.getList().isEmpty()){
//            fireProperychange(THEBATTLEHAS BEEN WON BY THE PLAYER)
        }

        if(activeMainCharacterList.isEmpty()){
            playerHasWonBattle.firePropertyChange("BattleWonByPlayer",false,true);
        }



    }

    private void removeKoEnemiesFromList() {
        enemyList.getList().removeIf(InterEnemy::isKO);
    }

    private void removeKoMainCharactersFromList() {
        activeMainCharacterList.removeIf(InterMainCharacter::isKO);
    }

    public void playerWonBattleSequence() {
    }


    public InterItem retrieveItemFromPlayerVault(ItemType type) throws InvalidSelectionException {
        InterItem itemToReturn = this.getPlayer().getPlayerVault().retrieveItem(type);

        if(itemToReturn == null) throw new InvalidSelectionException("The player doesn't have a/an " + type + " in their inventory!");

        return itemToReturn;
    }


    public void tryToAttack(){

    }


    public void tryToSelectAttack(AttackType attackType) throws InvalidAttackException {
        // throws exceptions if no FP left. or if character KO.
        this.getCurrentTurnMainCharacter().validateAttack(attackType);

        AttackTurn turn = (AttackTurn) this.getCurrentTurn();

        turn.setAttackType(attackType);

    }

    public void tryToSelectEnemyToBeAttacked(int enemyIndex) throws InvalidTurnException {
        if(!(this.getCurrentTurn().getType() == TurnType.ATTACK)) throw new InvalidTurnException("Can't select enemy in the current turn state");
        AttackTurn turn = (AttackTurn) this.getCurrentTurn();
        turn.setEnemyNumberToAttack(enemyIndex);
    }


    public void finishGameRoutine() {

    }



    /* Getters and setters */

    /**
     * Gets one of the controller's Player main characters. (either marco or luis.)
     *
     * @param type the EntityType of the character to retrieve.
     * @return the players main character, according to the Type given.
     */
    public InterMainCharacter getPlayerMainCharacter(EntityType type) {

        for(InterMainCharacter character : activeMainCharacterList){
            if(character.getType() == type) return character;
        }
        // TODO: add exception
        return null;
    }


    /**
     * Gets the Players' Enemy List.
     **/
    public EnemyList getEnemyList() {
        return this.enemyList;
    }


    /**
     * Gets the gameFinished status.
     *
     * @return the boolean indicating if the whole game has finished.
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Sets the gameFinished status
     *
     * @param gameFinished the boolean indicating if the game is finished.
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Gets the Owner of the next turn to be played.
     * The owner can be either the player, or the enemy.
     *
     * @return The TurnOwner type of the next turn.
     */
    public TurnOwner calculateNextTurnOwner() {
        int currentId = getCurrentTurnOwner().ordinal();

        if(currentId == 2){
            // Marco
            return TurnOwner.getTurnOwnerFromId(0);
        }

        // Adds 1 to the current turn owner's id, and asks for .
        return TurnOwner.getTurnOwnerFromId(currentId+1);
    }

    public TurnOwner getNextTurnOwner() {
        return nextTurnOwner;
    }

    /**
     * Sets the next turn owner
     *
     * @param nextTurnOwner the TurnOwner type of the next turn.
     */
    public void setNextTurnOwner(TurnOwner nextTurnOwner) {
        this.nextTurnOwner = nextTurnOwner;
    }

    /**
     * gets the current turn owner.
     *
     * @return TurnOwner describing the current turn owner.
     */
    public TurnOwner getCurrentTurnOwner() {
        return currentTurnOwner;
    }

    /**
     * Ges the game's winner.
     *
     * @return A boolean denoting the games' winner.
     * True -> Player win
     * False -> Enemy Win
     */
    public boolean getPlayerWon() {
        return playerWon;
    }

    /**
     * Sets the game's winner.
     *
     * @param playerWon A boolean denoting the games' winner.
     *               True -> Player win
     *               False -> Enemy Win
     */
    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    /**
     * Gets the controller's current played turn.
     *
     * @return The current turn being played.
     */
    public InterTurn getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Sets (updates) the current turn.
     *
     * @param currentTurn The InterTurn to update the current turn with.
     */
    public void setCurrentTurn(InterTurn currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Gets The controller's main player.
     *
     * @return The controller's current main player.
     */
    public Player getPlayer() {
        return mainPlayer;
    }

    /**
     * Sets the controller's Player.
     *
     * @param player the player to be set.
     */
    public void setPlayer(Player player) {
        this.mainPlayer = player;
    }

    /**
     * Creates a Player with a given name, and then sets the controller's player to be that player. .
     *
     * @param playerName the name of the player to be created and set.
     */
    public void setPlayer(String playerName) {
        this.mainPlayer = new Player(playerName);
    }

    public void setCurrentTurnOwner(TurnOwner currentTurnOwner) {
        this.currentTurnOwner = currentTurnOwner;
    }


    public void setCurrentBattle(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    public InterPhase getCurrentPhase() {
        return phase;
    }


    public PhaseType getCurrentPhaseType(){
        return this.phase.getType();
    }

}

package aventurasdemarcoyluis.backend.controller;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.view.PhaseChangedHandler;
import aventurasdemarcoyluis.backend.controller.phases.*;
import aventurasdemarcoyluis.backend.controller.turns.*;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.InterEntity;
import aventurasdemarcoyluis.backend.model.Player;
import aventurasdemarcoyluis.backend.model.enemies.*;
import aventurasdemarcoyluis.backend.model.items.InterItem;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.backend.model.maincharacters.Luis;
import aventurasdemarcoyluis.backend.model.maincharacters.Marco;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main Class of the controller Module.
 * Handles all the controller logic, integrating Turns, Battles, Vaults and the player.
 */
public class
GameController implements InterController {

    private Player mainPlayer;
    private InterTurn currentTurn = null;

    private TurnOwner currentTurnOwner;
    private TurnOwner nextTurnOwner;

    private InterMainCharacter currentInvolvedMainCharacter = null;

    private Battle currentBattle = null;

    private final ArrayList<InterMainCharacter> mainCharacterList;

    private final ActiveMainCharacterList activeMainCharacterList = new ActiveMainCharacterList(this);;

    private final EnemyList enemyList;

    private boolean playerWon;
    private boolean gameFinished;

    private Phase phase;

    private final PhaseChangedHandler phaseChangedHandler;
    private final PropertyChangeSupport phaseChangedHandlerSupport;


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
        this.nextTurnOwner = calculateNextTurnOwner(getCurrentTurnOwner());

        this.mainPlayer = new Player("J1", this);

        this.mainCharacterList = new ArrayList<>();


        activeMainCharacterList.initialSetup();


        mainCharacterList.add(mainPlayer.getMarco());
        mainCharacterList.add(mainPlayer.getLuis());

        this.enemyList = new EnemyList(this);

        this.playerWon = false;
        this.gameFinished = false;

        // we set up the phase changed listener.
        phaseChangedHandler = new PhaseChangedHandler();
        phaseChangedHandler.setController(this);
        phaseChangedHandlerSupport = new PropertyChangeSupport(this);

        // we subscribe to the phase changed listener in case a controller has been specified.
        phaseChangedHandlerSupport.addPropertyChangeListener(phaseChangedHandler);

        this.phase = new StartBattlePhase(this);

    }

    /**
     * GameController Constructor
     * Note: When using this constructor, runFirstBattle(); should be executed
     * To initialize the controller and the phase logic.
     * @param playerName The name of the player playing the game.
     */
    public GameController(String playerName) {

        // The player's mario always plays first
        this.currentTurnOwner = TurnOwner.MARCO;
        // This should be luis
        this.nextTurnOwner = calculateNextTurnOwner(getCurrentTurnOwner());

        this.mainPlayer = new Player(playerName, this);

        this.mainCharacterList = new ArrayList<>();

        mainCharacterList.add(mainPlayer.getMarco());
        mainCharacterList.add(mainPlayer.getLuis());

        activeMainCharacterList.initialSetup();

        this.enemyList = new EnemyList(this);

        this.playerWon = false;
        this.gameFinished = false;

        // we set up the phase changed listener.
        phaseChangedHandler = new PhaseChangedHandler();
        phaseChangedHandler.setController(this);
        phaseChangedHandlerSupport = new PropertyChangeSupport(this);

        // we subscribe to the phase changed listener in case a controller has been specified.
        phaseChangedHandlerSupport.addPropertyChangeListener(phaseChangedHandler);

    }

    public void runFirstBattle() {
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
                Marco returnCharacter = new Marco(atk, def, fp, maxFP, hp, maxHP, lvl);
                return returnCharacter;
            }
            case LUIS -> {
                Luis returnCharacter = new Luis(atk, def, fp, maxFP, hp, maxHP, lvl);
                return returnCharacter;
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
    public InterEnemy createEnemy(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int lvl) {
        switch (type) {
            case GOOMBA -> {
                InterEnemy returnEnemy = new Goomba(atk, def, hp, maxHP, lvl);
                return returnEnemy;
            }
            case BOO -> {
                InterEnemy returnEnemy = new Boo(atk, def, hp, maxHP, lvl);
                return returnEnemy;
            }
            case SPINY -> {
                InterEnemy returnEnemy = new Spiny(atk, def, hp, maxHP, lvl);
                return returnEnemy;
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

        this.getPlayer().addAnItem(ItemType.HONEYSYRUP,3);
        this.getPlayer().addAnItem(ItemType.REDMUSHROOM,3);


        // After the levelup routine, the player's characters will be updated, but not the active main characters yet.
        this.activeMainCharacterList.restoreList(getPlayer().getMarco(),getPlayer().getLuis());


        currentBattle.setRandomEnemyList();




    }

    /**
     * Sends the message to the controller's player to lvlUP.
     * Note: does nothing if the player's current lvl is 1. this is so we don't add more than 3 items on the
     * players first turn.
     */
    @Override
    public void playerLvlUp() {
        this.mainPlayer.setPlayerLvl(mainPlayer.getPlayerLvl()+1);
        if (this.getPlayer().getPlayerLvl() == 1) {
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
    public void tryToSelectNewTurnKind(@NotNull TurnType selection) throws InvalidSelectionException {

        setUpInvolvedMainCharacter();

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

                InterTurn itemTurn = new ItemTurn(this);
                this.setCurrentTurn(itemTurn);

            }
            case PASSING -> {
                PassingTurn passingTurn = new PassingTurn(this);
                this.setCurrentTurn(passingTurn);
            }
            case ENEMY -> {
                EnemyTurn enemyTurn = new EnemyTurn(this);
                this.setCurrentTurn(enemyTurn);
            }

            default -> this.setCurrentTurn(null);
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
    public void finishTurn() {

        // This handles the finish game logic.
        // check if the player is KO after the turn.
        // TODO: this should be handled by the isKO method in battle class
        if (this.activeMainCharacterList.getActiveMainCharacterList().isEmpty()) {
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



        // As the next turn isn't of an Enemy, and the player hasn't selected wich
        // turn kind they want to perform next, we set it to null.
        this.setCurrentTurn(null);

        TurnOwner nextOwner = calculateNextTurnOwner(getCurrentTurnOwner());

        // Set's the next turn's owner according to what is specified
        this.setCurrentTurnOwner(nextOwner);
        // As the current turn owner has been updated, also update the new next turn owner
        this.setNextTurnOwner(calculateNextTurnOwner(nextOwner));


    }


    /**
     * Sets up the current involved main character by looking up the TurnOwner
     */
    private void setUpInvolvedMainCharacter() {
        try {
            this.currentInvolvedMainCharacter = activeMainCharacterList.retrieveMainCharacter(convertTurnOwnerToEntityType(this.currentTurnOwner));
        }catch (InvalidSelectionException e){
            e.printStackTrace();
        }
    }


    /**
     * Gets the InterMainCharacter involved in the current turn.
     * @return the InterMainCharacter involved in the current turn.
     */
    public InterMainCharacter getCurrentTurnMainCharacter(){
        TurnOwner turnOwner = getCurrentTurnOwner();

        switch (turnOwner){
            case LUIS -> {return getPlayerMainCharacter(EntityType.LUIS);}
            case MARCO -> {return getPlayerMainCharacter(EntityType.MARCO);}
        }
        return null;
    }

    /**
     * Gets a random active main character
     * @return a random active main character
     */
    public InterMainCharacter getRandomMainCharacterFromActiveList(){
        Random randomizer = new Random();
        return activeMainCharacterList.getActiveMainCharacterList().get(randomizer.nextInt(activeMainCharacterList.getActiveMainCharacterList().size()));
    }


    /**
     * Tries to change phase to a given phase.
     * @param phaseToChangeTo The type of phase to change to
     * @throws InvalidTransitionException In case the phase change is illegal
     */
    public void tryToChangePhase(Phase phaseToChangeTo) throws InvalidTransitionException {
        // In case this is the first battle played
        if(this.phase == null && this.mainPlayer.getBattleNumber() == 1){
            this.setPhase(phaseToChangeTo);
            return;
        }


        // If the phase transition is illegal
        if(!this.phase.validatePhaseChange(phaseToChangeTo)){
            throw new InvalidTransitionException("You can't Transition phase at this time!");
        }

        // We fire a property change.
        phaseChangedHandlerSupport.firePropertyChange("PhaseChange", this.getCurrentPhase(),phaseToChangeTo);
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

        try {
            tryToChangePhase(new FinishGamePhase(this));
        }catch (Exception ignored){}

    }


    /**
     * Sets the given phase to the controller
     * @param phase the phase to be set
     */
    @Override
    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    /**
     * Retrieves an item from the vault
     * @param type the type of itrm to retrieve
     * @return the item looked for
     * @throws InvalidSelectionException in case there is no item left.
     */
    public InterItem retrieveItemFromPlayerVault(ItemType type) throws InvalidSelectionException {
        InterItem itemToReturn = this.getPlayer().getPlayerVault().retrieveItem(type);

        if(itemToReturn == null) throw new InvalidSelectionException("The player doesn't have a/an " + type + " in their inventory!");

        return itemToReturn;
    }


    /**
     * tries to change phase
     * @param phase the phase to chamge to.
     */
    public void toNextPhase(Phase phase){
        this.phase.toNextPhase(phase);
    }


    /* Getters and setters */

    /**
     * Gets one of the controller's Player main characters. (either marco or luis.)
     *
     * @param type the EntityType of the character to retrieve.
     * @return the players main character, according to the Type given.
     */
    public InterMainCharacter getPlayerMainCharacter(EntityType type) {

        for(InterMainCharacter character : activeMainCharacterList.getActiveMainCharacterList()){
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
    public TurnOwner calculateNextTurnOwner(TurnOwner aCurrentTurnOwner) {

        TurnOwner expectedNextOwner;

        int currentId = aCurrentTurnOwner.ordinal();

        // If the index is 3, i have overshoot and should return to the begining.
        try {
            expectedNextOwner = TurnOwner.getTurnOwnerFromId(currentId + 1);
        }catch (Exception e){
            expectedNextOwner = TurnOwner.getTurnOwnerFromId(0);
        }


        // if there is a KO character in the current battle
        if(activeMainCharacterList.getActiveMainCharacterList().size() == 1){
            // no se si get(0) funciona. no se si los indices se reordenan o no. Si no, se puede hacer con un try y despues get 1 xddd
            EntityType notKoCharacter = activeMainCharacterList.getActiveMainCharacterList().get(0).getType();
            EntityType koCharacter = notKoCharacter==EntityType.MARCO? EntityType.LUIS : EntityType.MARCO;

            EntityType expectedNextOwnerEntity = convertTurnOwnerToEntityType(expectedNextOwner);

            // Si el turno del personaje que se esperaba, es del personaje que está KO, saltarlo.
            if (expectedNextOwnerEntity==koCharacter){
                switch (koCharacter){
                    // Si el personaje KO es luis, me lo salto y paso directo de marco -> Enemy
                    case LUIS -> {return TurnOwner.ENEMY;}
                    // Si el Personaje KO es marco, me lo salto y paso directo desde Enemy -> Luis
                    case MARCO -> {return TurnOwner.LUIS;}
                }
            }
        }
        // Adds 1 to the current turn owner's id, and asks for .
        return expectedNextOwner;
    }

    /**
     * gets the turn owner
     * @return the current turn owner
     */
    public TurnOwner getNextTurnOwner() {
        return nextTurnOwner;
    }

    /**
     * Converts a TURNOWNER to an ENTITYTYPE, if valid, else returns null.
     * @param turnOwner the turnowner to convert.
     * @return The converted enum type
     */
    public EntityType convertTurnOwnerToEntityType(TurnOwner turnOwner){
        switch (turnOwner){
            case MARCO -> {return EntityType.MARCO;}
            case LUIS -> {return EntityType.LUIS;}
        }
        return null;
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
        this.mainPlayer = new Player(playerName, this);
    }

    /**
     * Sets the current turn owner
     * @param currentTurnOwner the current turn owner
     */
    public void setCurrentTurnOwner(TurnOwner currentTurnOwner) {
        this.currentTurnOwner = currentTurnOwner;
    }

    /**
     * Sets the current battle
     * @param currentBattle the battle to be set
     */
    public void setCurrentBattle(Battle currentBattle) {
        this.currentBattle = currentBattle;
    }

    /**
     * gets the current phase
     * @return the current phase
     */
    public Phase getCurrentPhase() {
        return phase;
    }

    /**
     * Gets the current list of active Main characters
     * @return  the current list of active Main characters
     */
    public ActiveMainCharacterList getActiveMainCharacterList() {
        return activeMainCharacterList;
    }

    /**
     * Tries to perform a main character attack
     * @param attackSelection The kind of attack to perform
     * @param attackingMainCharacter the main character attacking
     * @param attackedEnemy the enemy attacked
     * @throws InvalidAttackException if the attack is not valid
     */
    public void tryToMakeCharacterAttack(AttackType attackSelection, InterMainCharacter attackingMainCharacter, InterEnemy attackedEnemy) throws InvalidAttackException {
        if(attackingMainCharacter.getType() == EntityType.LUIS && attackedEnemy.getType() == EntityType.BOO){
            throw new InvalidAttackException("Luis can't attack boo");
        }
        switch (attackSelection){
            case JUMP -> {
                if(attackingMainCharacter.getType() == EntityType.LUIS){
                    Luis luisAttackingMainCharacter  = (Luis) attackingMainCharacter;
                    luisAttackingMainCharacter.jumpAttack((InterGoombaSpiny) attackedEnemy);
                }
                if(attackingMainCharacter.getType() == EntityType.MARCO){
                    Marco luisAttackingMainCharacter  = (Marco) attackingMainCharacter;
                    luisAttackingMainCharacter.jumpAttack((attackedEnemy));
                }
            }
            case HAMMER -> {
                if(attackingMainCharacter.getType() == EntityType.LUIS){
                    Luis luisAttackingMainCharacter  = (Luis) attackingMainCharacter;
                    luisAttackingMainCharacter.hammerAttack((InterGoombaSpiny) attackedEnemy);
                }
                if(attackingMainCharacter.getType() == EntityType.MARCO){
                    Marco luisAttackingMainCharacter  = (Marco) attackingMainCharacter;
                    luisAttackingMainCharacter.hammerAttack((attackedEnemy));
                }
            }
        }

    }


}

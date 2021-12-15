package aventurasdemarcoyluis.backend.controller;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Implements a controller's main methods.
 */
public interface InterController {
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
    InterMainCharacter createMainCharacter(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int fp, int maxFP, int lvl);

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
    InterEnemy createEnemy(@NotNull EntityType type, double atk, double def, double hp, double maxHP, int lvl);

    /**
     * Adds an amount of 1 item of a given type to the controler player's vault.
     *
     * @param item The ItemType to add.
     */
    void addItemToPlayer(ItemType item);

    /**
     * Runs the set-up routine for generating a new battle.
     * 1. Creates a battle instance
     * 2. increases the player's battle number
     * 3. Restores the player's fp and hp to max before the battle begins
     * 4. Generates the random enemy list to fight, in correspondence to the current Battlenumber.
     * 5. Adds the initial items (3 Honey and 3 mushroom) to the player in the player's first battle.
     * 6. Runs the player lvlUp routine, in case this isn't the first battle played.
     */
    void createAndSetNewBattle() throws InvalidTransitionException;

    /**
     * Sends the message to the controller's player to lvlUP.
     * Note: does nothing if the player's current lvl is 1. this is so we don't add more than 3 items on the
     * players first turn.
     */
    void playerLvlUp();

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
    void tryToSelectNewTurnKind(@NotNull TurnType selection) throws InvalidSelectionException;

    /**
     * Finishes the controller's player turn.
     * <p>
     * Also, checks if the player has won or lost (according to the win or lose conditions)
     * In case the player has won, executes the playerWinning sequence.
     * Something analogous happens when the player has lost.
     */
    void finishTurn() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException;

    /**
     * Sequence to be executed when a player wins the game.
     * sets the controller's winner to true (Player)
     * Prints a congrats message to the screen.
     */
    void playerWinningSequence();

    /**
     * Sequence to be executed when a player loses the game.
     * sets the controller's winner to false (Enemies win)
     * Prints a sad message to the screen.
     */
    void playerLosingSequence();

    void setPhase(Phase phase) throws InvalidTransitionException;
}

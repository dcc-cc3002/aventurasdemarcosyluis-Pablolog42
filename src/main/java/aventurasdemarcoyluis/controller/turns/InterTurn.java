package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interface that contains the main methods of a Turn in the game.
 * is implemented b all turn-related subclasses.
 */
public interface InterTurn {

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     *
     * @throws IOException Excepción en caso de error en input de texto.
     **/
    void main() throws InvalidSelectionException, InvalidAttackException;

    /**
     * Returns the array of mainCharacters still present (not KO) in the turn.
     *
     * @return Returns the array of mainCharacters
     */
    ArrayList<InterMainCharacter> getCurrentTurnMainCharacters();

    /**
     * Returns the type of turn played.
     *
     * @return Type of turn played.
     */
    TurnType getType();

    /**
     * Sets a Buffered Reader stream as an input to a turn.
     * The BufferedReader can be instanced with a string
     * or with the use of the System.in input.
     *
     * @param reader The reader to set.
     */
    void setReader(BufferedReader reader);

    /**
     * Returns the current turn's Buffer Reader
     *
     * @return The current turn reader.
     */
    BufferedReader getReader();

    /**
     * Gets the current turn's game controller
     *
     * @return The current controller.
     */
    GameController getController();

    /**
     * Gets the current turn's "Involved Character"
     * <p>
     * The involved character is the mainCharacter of the player which is being currently
     * acted upon (either by using an item on them, or letting them attack an enemy).
     * <p>
     * Note that in the "Passing" turn, there is no action being performed, and thus,
     * the Involved Character should return null.
     *
     * @return The current Involved Character.
     */
    InterMainCharacter getInvolvedMainCharacter();


}

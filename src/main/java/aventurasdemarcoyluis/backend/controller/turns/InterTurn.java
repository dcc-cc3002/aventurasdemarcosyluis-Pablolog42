package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;

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
    void main() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException;

    /**
     * Returns the type of turn played.
     *
     * @return Type of turn played.
     */
    TurnType getType();



}

package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;

/**
 * Abstract representation of a Turn in the game.
 * It and all it's subclasses implement the InterTurn interface.
 */
public abstract class AbstractTurn implements InterTurn {

    private final GameController controller;
    private BufferedReader reader;
    protected InterMainCharacter involvedMainCharacter = null;

    /**
     * AbstractTurn Constructor
     *
     * @param controller the game controller controlling the turn.
     */
    public AbstractTurn(GameController controller) {
        this.controller = controller;
    }

    /**
     * Returns the current turn's Buffer Reader
     *
     * @return The current turn reader.
     */
    @Override
    public BufferedReader getReader() {
        return reader;
    }

    /**
     * Sets a Buffered Reader stream as an input to a turn.
     * The BufferedReader can be instanced with a string
     * or with the use of the System.in input.
     *
     * @param reader The reader to set.
     */
    @Override
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Gets the current turn's game controller
     *
     * @return The current controller.
     */
    @Override
    public GameController getController() {
        return controller;
    }


    public void setInvolvedMainCharacter(InterMainCharacter playerMainCharacter){
        this.involvedMainCharacter = playerMainCharacter;
    }


}

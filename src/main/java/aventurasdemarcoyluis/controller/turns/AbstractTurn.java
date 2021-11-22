package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Abstract representation of a Turn in the game.
 * It and all it's subclasses implement the InterTurn interface.
 */
public abstract class AbstractTurn implements InterTurn {

    private final GameController controller;
    private BufferedReader reader;

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

    /**
     * Returns the array of mainCharacters still present (not KO) in the turn.
     *
     * @return Returns the array of mainCharacters
     */
    @Override
    public ArrayList<InterMainCharacter> getCurrentTurnMainCharacters() {
        ArrayList<InterMainCharacter> currentTurnMainCharacters = new ArrayList<>();

        // Agrego solo los personajes principales que no están KO.
        // este metodo es el que se encarga de cumplir con el requisito
        // "Quitar a un personaje del "Turno" cuando esté KO"
        for (InterMainCharacter character : this.controller.getPlayer().getMainCharacterArrayList()) {
            if (!character.isKO()) currentTurnMainCharacters.add(character);
        }
        return currentTurnMainCharacters;

    }


}

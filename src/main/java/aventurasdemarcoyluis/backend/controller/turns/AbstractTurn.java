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
    protected InterMainCharacter involvedMainCharacter = null;

    /**
     * AbstractTurn Constructor
     *
     * @param controller the game controller controlling the turn.
     */
    public AbstractTurn(GameController controller) {
        this.controller = controller;
    }






}

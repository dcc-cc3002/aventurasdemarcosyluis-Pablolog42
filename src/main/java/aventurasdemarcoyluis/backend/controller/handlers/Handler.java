package aventurasdemarcoyluis.backend.controller.handlers;

import aventurasdemarcoyluis.backend.controller.GameController;

import java.beans.PropertyChangeListener;

/**
 * Interface denoting the handling of observers in the game. Extends the PropertyChangeListener interface.
 */
public interface Handler extends PropertyChangeListener {


    /**
     * Sets the handlers' controller.
     * @param controller The controller to set.
     */
    void setController(GameController controller);
}

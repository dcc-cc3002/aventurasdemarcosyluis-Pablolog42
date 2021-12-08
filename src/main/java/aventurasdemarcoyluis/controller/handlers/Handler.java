package aventurasdemarcoyluis.controller.handlers;

import aventurasdemarcoyluis.controller.GameController;

import java.beans.PropertyChangeListener;

public interface Handler extends PropertyChangeListener {

    //TODO comentar la interfaz entera (junto con la parte de arriba)

    /**
     * Sets the handlers' controller.
     * @param controller The controller to set.
     */
    void setController(GameController controller);
}

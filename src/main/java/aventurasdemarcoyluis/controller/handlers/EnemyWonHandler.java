package aventurasdemarcoyluis.controller.handlers;

import aventurasdemarcoyluis.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EnemyWonHandler implements Handler {

    private GameController controller=null;



    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    /**
     * Sets the handlers' controller.
     *
     * @param controller The controller to set.
     */
    @Override
    public void setController(GameController controller) {
        this.controller=controller;
    }
}

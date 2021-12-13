package aventurasdemarcoyluis.view;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.handlers.Handler;
import aventurasdemarcoyluis.backend.controller.phases.Phase;

import java.beans.PropertyChangeEvent;

public class PhaseChangedHandler implements Handler {

    private GameController controller = null;


    /**
     * Sets the handlers' controller.
     *
     * @param controller The controller to set.
     */
    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO: Actualizar el label de la gui con un observer.
        Phase oldPhase = (Phase) evt.getOldValue();
        Phase newPhase = (Phase) evt.getNewValue();

        System.out.println("ALO HUBO UN CAMBIO DE FASE DE PANA DESDE " + oldPhase.getType() + " a " + newPhase.getType() );
    }
}

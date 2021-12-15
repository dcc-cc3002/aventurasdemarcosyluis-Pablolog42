package aventurasdemarcoyluis.view;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.handlers.Handler;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.view.MainGUI;

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
        Phase oldPhase = (Phase) evt.getOldValue();
        Phase newPhase = (Phase) evt.getNewValue();

        System.out.println("Cambio de fase Detectado, desde " + oldPhase.getType() + " a " + newPhase.getType() );

//        //Sin esto los test no corren
//        try {
//            MainGUI.updatePhaseButton(newPhase.getType());
//        }catch (Exception ignored){
//
//        }
    }
}

package aventurasdemarcoyluis.controller.handlers;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.InterEntity;

import java.beans.PropertyChangeEvent;

public class EntityKoHandler implements Handler{

    private GameController controller;

    public EntityKoHandler(GameController controller){
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
        controller.updateEntityKoStatus((InterEntity) evt.getNewValue());
    }
}

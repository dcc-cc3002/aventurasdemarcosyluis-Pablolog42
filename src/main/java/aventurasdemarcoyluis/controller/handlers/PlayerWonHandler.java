package aventurasdemarcoyluis.controller.handlers;

import aventurasdemarcoyluis.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerWonHandler implements PropertyChangeListener {

    private final GameController controller;

    public PlayerWonHandler(GameController controller){
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
        
    }
}

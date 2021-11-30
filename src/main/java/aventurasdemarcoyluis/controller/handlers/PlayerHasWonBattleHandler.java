package aventurasdemarcoyluis.controller.handlers;

import aventurasdemarcoyluis.controller.GameController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerHasWonBattleHandler implements PropertyChangeListener {

    private final GameController controller;


    public PlayerHasWonBattleHandler(GameController controller) {
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
        controller.playerWonBattleSequence();
    }
}

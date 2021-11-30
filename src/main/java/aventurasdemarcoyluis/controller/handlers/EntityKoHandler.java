package aventurasdemarcoyluis.controller.handlers;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EntityKoHandler implements PropertyChangeListener {

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
        InterMainCharacter characterNowKo = (InterMainCharacter) evt.getNewValue();
        System.out.println(characterNowKo + "IS KO!");
    }
}

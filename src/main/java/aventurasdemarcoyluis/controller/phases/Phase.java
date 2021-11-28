package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;

public abstract class Phase implements InterPhase{

    protected GameController controller;


    public Phase(GameController controller){
        this.controller = controller;
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public String toString() {
        return "Phase";
    }
}

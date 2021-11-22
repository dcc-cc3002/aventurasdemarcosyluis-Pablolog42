package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;

public class Phase {
    protected GameController controller;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void changePhase(Phase phase){
        controller.setPhase(phase);
    }



}

package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.Phase;

public class StartPassingTurnPhase extends Phase {
    public void toFinishTurnPhase(){
        controller.tryToChangePhase(new FinishTurnPhase());
    }

    @Override
    public String toString() {
        return "StartPassingTurnPhase";
    }
}

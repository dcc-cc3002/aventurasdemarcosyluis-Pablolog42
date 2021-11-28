package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.Phase;

public class AttackPhase extends Phase {
    public void toFinishTurnPhase(){
        controller.tryToChangePhase(new FinishTurnPhase());
    }

    @Override
    public String toString() {
        return "AttackPhase";
    }

}

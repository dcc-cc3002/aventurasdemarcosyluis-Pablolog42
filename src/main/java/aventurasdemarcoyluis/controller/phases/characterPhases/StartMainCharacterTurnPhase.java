package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;

public class StartMainCharacterTurnPhase extends Phase {
    public void toSelectTurnTypePhase(){
        controller.changePhase(new SelectTurnTypePhase());
    }
}

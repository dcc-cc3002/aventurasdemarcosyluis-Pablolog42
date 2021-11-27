package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.characterPhases.UseItemPhase;

public class SelectItemPhase extends Phase {
    public void toUseItemPhase(){
        controller.changePhase(new UseItemPhase());
    }
}

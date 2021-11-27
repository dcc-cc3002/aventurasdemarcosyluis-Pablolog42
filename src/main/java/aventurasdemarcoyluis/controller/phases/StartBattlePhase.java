package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.phases.characterPhases.SelectTurnTypePhase;

public class StartBattlePhase extends Phase{

    public void toSelectTurnTypePhase(){
        this.controller.changePhase(new SelectTurnTypePhase());
    }

}

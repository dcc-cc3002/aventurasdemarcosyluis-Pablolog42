package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectTurnTypePhase;

public class StartBattlePhase extends Phase{

    public StartBattlePhase(){
        super();
        this.canTransitionPhase = false;
    }

    // Prerequisites to transition:
    // 1. the lvl up routine should be performed.
    public void toSelectTurnTypePhase(){
        controller.tryToChangePhase(new WaitSelectTurnTypePhase());
    }


    public void battleSetUpRoutine(){
        controller.createAndSetNewBattle();
        canTransitionPhase = true;
    }

}

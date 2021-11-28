package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;

public class FinishBattlePhase extends Phase {

    //After a battle finishes, either the player has won (in case n>=5)
    public void toFinishGamePhase(){
        controller.tryToChangePhase(new FinishGamePhase());
    }

    //
    public void toStartBattlePhase(){
        controller.tryToChangePhase((new StartBattlePhase()));
    }
}

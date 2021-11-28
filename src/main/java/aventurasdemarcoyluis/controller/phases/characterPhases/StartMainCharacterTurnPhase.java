package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;

public class StartMainCharacterTurnPhase extends Phase {

    public StartMainCharacterTurnPhase(){
        super();
        this.canTransitionPhase = false;
        this.canStartNewTurn = false;
    }


    public void toWaitSelectTurnTypePhase(){
        controller.tryToChangePhase(new WaitSelectTurnTypePhase());
    }







    @Override
    public String toString() {
        return "StartMainCharacterTurnPhase";
    }

}

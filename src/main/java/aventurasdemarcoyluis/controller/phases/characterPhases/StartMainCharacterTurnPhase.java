package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.turns.TurnType;

public class StartMainCharacterTurnPhase extends Phase {

    public StartMainCharacterTurnPhase(){
        super();
        this.canTransitionPhase = false;
        this.canStartNewTurn = false;
    }

    public void toSelectTurnTypePhase(){
        controller.changePhase(new SelectTurnTypePhase());
    }



    // I can only transition to a new phase when i have selected the turn i want to play.
    public void selectTurnKind(TurnType type){
        controller.selectNewTurnKind(type);
        canStartNewTurn = true;



    }



    @Override
    public String toString() {
        return "StartMainCharacterTurnPhase";
    }

}

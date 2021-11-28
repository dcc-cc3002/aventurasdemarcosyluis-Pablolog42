package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.turns.TurnType;


public class WaitSelectTurnTypePhase extends Phase {

    private boolean playerNotKORequisite = false;
    private boolean turnSelected = false;

    public WaitSelectTurnTypePhase(){
        controller.configureCurrentInvolvedMainCharacter();
    }

    // I can only transition to a new phase when I have selected the turn I want to play.
    public void selectTurnKind(TurnType selection){
        try {
            controller.tryToSelectNewTurnKind(selection);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        // As a turn has been successfully selected, the phase can transition.
        canStartNewTurn = true;
        turnSelected = true;
    }

    // Checks if the transition requirements are achieved, then transitions the phase of the game.
    public void toSelectedTurnPhase(){
        switch (controller.getCurrentTurn().getType()){
            case ITEM -> {
                checkForItemTurnRequisites();
                controller.tryToChangePhase(new WaitSelectItemPhase());
            }
            case ATTACK -> controller.tryToChangePhase(new WaitSelectEnemyToBeAttackedPhase());
            case ENEMY ->  controller.tryToChangePhase(new StartPassingTurnPhase());
        }
    }

    private void checkForItemTurnRequisites() {
        if(!controller.getCurrentTurn().getInvolvedMainCharacter().isKO()) {
            playerNotKORequisite=true;
        }


    }


    @Override
    public String toString() {
        return "SelectAttackTurnPhase";
    }
}

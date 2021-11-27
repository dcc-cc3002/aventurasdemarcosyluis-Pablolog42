package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;


public class SelectTurnTypePhase extends Phase {

    public void toSelectItemPhase(){
        controller.changePhase(new SelectItemPhase());
    }
    public void toSelectEnemyToAttackPhase(){
        controller.changePhase(new SelectEnemyToBeAttackedPhase());
    }

    public void toPassingTurn(){
        controller.changePhase(new StartPassingTurnPhase());
    }


    @Override
    public String toString() {
        return "SelectAttackTurnPhase";
    }
}

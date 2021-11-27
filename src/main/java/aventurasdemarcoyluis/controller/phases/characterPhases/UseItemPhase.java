package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.Phase;

public class UseItemPhase extends Phase {
    public void toFinishTurnPhase(){
        controller.changePhase(new FinishTurnPhase());
    }


    @Override
    public String toString() {
        return "UseItemPhase";
    }

    public static class SelectEnemyToBeAttackedPhase extends Phase {
        public void toSelectAttackTypePhase(){
            //TODO: this should throw an invalid transition if there are no more enemies to attack.
            controller.changePhase(new SelectAttackTypePhase());


        }

        @Override
        public String toString() {
            return "SelectEnemyToBeAttackedPhase";
        }
    }
}

package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.enemyPhases.SelectEnemyToAttackPhase;

public class SelectTurnTypePhase extends Phase {

    public void toSelectItemPhase(){
        controller.changePhase(new SelectItemPhase());
    }
    public void toSelectEnemyToAttackPhase(){
        controller.changePhase(new SelectEnemyToAttackPhase());
    }

    public void toPassingTurn(){
        controller.changePhase(new StartPassingTurnPhase());
    }
}

package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.enemyPhases.SelectEnemyToAttackPhase;

public class StartEnemyTurnPhase extends Phase {
    public void toSelectRandomEnemyToBeAttacker(){
        controller.changePhase(new SelectEnemyToAttackPhase());
    }
}

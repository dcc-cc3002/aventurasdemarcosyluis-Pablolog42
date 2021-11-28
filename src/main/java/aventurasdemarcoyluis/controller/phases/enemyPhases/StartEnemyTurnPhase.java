package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.characterPhases.UseItemPhase;


public class StartEnemyTurnPhase extends Phase {
    public void toSelectRandomEnemyToBeAttacker(){
        controller.tryToChangePhase(new SelectEnemyWhoWillAttackPhase());
    }
}

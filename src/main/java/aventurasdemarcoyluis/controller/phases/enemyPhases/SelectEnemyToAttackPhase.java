package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.characterPhases.SelectAttackTypePhase;

public class SelectEnemyToAttackPhase extends Phase {
    public void toSelectAttackTypePhase(){
        //TODO: this should throw an invalid transition if there are no more enemies to attack.
        controller.changePhase(new SelectAttackTypePhase());
    }
}

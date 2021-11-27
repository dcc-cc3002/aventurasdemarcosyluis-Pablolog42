package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.enemyPhases.EnemyAttackPhase;
import aventurasdemarcoyluis.controller.phases.Phase;

public class SelectAttackTypePhase extends Phase {

    public void toEnemyAttackPhase(){
        controller.changePhase(new EnemyAttackPhase());
    }
}

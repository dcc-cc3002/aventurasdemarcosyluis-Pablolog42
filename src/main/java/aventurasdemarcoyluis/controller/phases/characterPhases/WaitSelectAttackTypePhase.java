package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.enemyPhases.EnemyAttackPhase;
import aventurasdemarcoyluis.controller.phases.Phase;

public class WaitSelectAttackTypePhase extends Phase {

    public void toEnemyAttackPhase(){
        controller.tryToChangePhase(new EnemyAttackPhase());
    }

    @Override
    public String toString() {
        return "WaitSelectAttackTypePhase";
    }

}

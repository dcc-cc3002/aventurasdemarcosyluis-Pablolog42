package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;

public class SelectEnemyToBeAttackedPhase extends Phase {
    public void toAttackPhase(){
        controller.changePhase(new AttackPhase());
    }
}

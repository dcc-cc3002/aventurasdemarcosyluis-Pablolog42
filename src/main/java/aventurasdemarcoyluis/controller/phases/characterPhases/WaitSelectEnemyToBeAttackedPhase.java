package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.phases.Phase;

public class WaitSelectEnemyToBeAttackedPhase extends Phase {
    public void toAttackPhase(){
        controller.tryToChangePhase(new AttackPhase());
    }
}

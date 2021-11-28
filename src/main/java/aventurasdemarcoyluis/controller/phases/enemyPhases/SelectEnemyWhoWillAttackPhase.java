package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.phases.Phase;

public class SelectEnemyWhoWillAttackPhase extends Phase {

public void toSelectCharacterToAttackPhase(){
    controller.tryToChangePhase(new SelectCharacterToAttackPhase());
}

}

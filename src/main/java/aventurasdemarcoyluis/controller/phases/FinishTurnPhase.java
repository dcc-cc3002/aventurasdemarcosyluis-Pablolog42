package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.phases.characterPhases.StartMainCharacterTurnPhase;
import aventurasdemarcoyluis.controller.phases.enemyPhases.StartEnemyTurnPhase;

public class FinishTurnPhase extends Phase{
    public void toEnemyTurnPhase(){
        controller.tryToChangePhase(new StartEnemyTurnPhase());
    }
    public void toNewMainCharacterPhase(){
        controller.tryToChangePhase(new StartMainCharacterTurnPhase());
    }
}

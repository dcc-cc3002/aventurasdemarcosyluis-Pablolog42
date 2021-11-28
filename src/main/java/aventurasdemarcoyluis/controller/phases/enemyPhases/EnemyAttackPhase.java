package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.phases.FinishBattlePhase;
import aventurasdemarcoyluis.controller.phases.FinishGamePhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.characterPhases.StartMainCharacterTurnPhase;

public class EnemyAttackPhase extends Phase {

    // Either the player is KO and the game has ended
    public void toFinishGamePhase(){
        controller.tryToChangePhase(new FinishGamePhase());
    }

    // There might also be enemies left, and the player isnt KO.
    // In this case, the battle continues.
    public void toStartMainCharacterTurnPhase(){
        controller.tryToChangePhase(new StartMainCharacterTurnPhase());
    }

    // Or finally, there might be no enemies left, and the player isn't KO.
    // In this case, the battle ends
    public void toBattleEndingPhase(){
        controller.tryToChangePhase(new FinishBattlePhase());
    }


    @Override
    public String toString() {
        return "enemyAttackPhase";
    }


}

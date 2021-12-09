package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.FinishBattlePhase;
import aventurasdemarcoyluis.controller.phases.FinishGamePhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.ItemType;

public class EnemyAttackPhase extends Phase {

    PhaseType phaseType = PhaseType.ENEMYATTACKPHASE;

    boolean isAttackCompleted=false;

    public EnemyAttackPhase(GameController controller) {
        super(controller);
    }


//
//    public void toFinishGamePhase(){
//        controller.tryToChangePhase(new FinishGamePhase());
//    }
//
//    public void toStartMainCharacterTurnPhase(){
//        controller.tryToChangePhase(new WaitSelectTurnTypePhase());
//    }
//
//    // Or finally, there might be no enemies left, and the player isn't KO.
//    // In this case, the battle ends
//    public void toBattleEndingPhase(){
//        controller.tryToChangePhase(new FinishBattlePhase());
//    }


    @Override
    public String toString() {
        return "enemyAttackPhase";
    }


    /**
     * Try to transition to next phase, according to the current
     * phase change prerequisites.
     *
     * @param phase The new phase to try to transition to.
     */
    @Override
    public void toNextPhase(Phase phase) {
        try {
            controller.tryToChangePhase(phase);
        } catch (InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        // Either the player is KO and the game has ended
        boolean r1 = phaseToBeChanged.getType() == PhaseType.FINISHGAMEPHASE;
        // There might also be enemies left, and the player isnt KO.
        // In this case, the battle continues.
        boolean r2 = phaseToBeChanged.getType() == PhaseType.WAITSELECTTURNTYPEPHASE;
        // Or finally, there might be no enemies left, and the player isn't KO.
        // In this case, the battle ends
        boolean r3 = phaseToBeChanged.getType() == PhaseType.FINISHBATTLEPHASE;
        return isAttackCompleted && (r1||r2||r3);
    }


    public void performAttack(){

    }


    /**
     * Gets the type oh the current phase.
     *
     * @return The current phase's type
     */
    @Override
    public PhaseType getType() {
        return phaseType;
    }



}

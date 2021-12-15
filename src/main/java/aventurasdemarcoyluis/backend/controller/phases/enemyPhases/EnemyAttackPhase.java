package aventurasdemarcoyluis.backend.controller.phases.enemyPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;

/**
 * Class denoting the phase in which an enemy will attack the player's main character.
 * Part of the Phases' "State" design patter implementation.
 */
public class EnemyAttackPhase extends Phase {

    PhaseType phaseType = PhaseType.ENEMYATTACKPHASE;

    boolean isAttackCompleted=false;

    /**
     * EnemyAttackPhase class constructor
     * @param controller the controller handling the game
     */
    public EnemyAttackPhase(GameController controller) {
        super(controller);
        performAttack();

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
        } catch (InvalidTransitionException e){ e.printStackTrace(); }
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        boolean r4 = phaseToBeChanged.getType() == PhaseType.FINISHTURNPHASE;
        return isAttackCompleted && (r4);
    }



    /**
     * Enemy implementation of to perform attack method.
     */
    @Override
    public void performAttack(){
        try {
            controller.tryToSelectNewTurnKind(TurnType.ENEMY);
            controller.getCurrentTurn().main();
        } catch (Exception e){ e.printStackTrace(); }
        isAttackCompleted = true;

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

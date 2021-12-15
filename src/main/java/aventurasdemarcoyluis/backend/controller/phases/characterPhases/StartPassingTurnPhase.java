package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.turns.PassingTurn;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;

/**
 * Class denoting a passing turn phase.
 * Part of the Phases' "State" design patter implementation.
 */
public class StartPassingTurnPhase extends Phase {

    PhaseType phaseType = PhaseType.STARTPASSINGPHASE;

    /**
     * StartPassingTurn constructor.
     * @param controller the controller handling the game.
     */
    public StartPassingTurnPhase(GameController controller) {
        super(controller);
        executePassingTurn();
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
        } catch (InvalidTransitionException e){e.printStackTrace();}
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        return phaseToBeChanged.getType() == PhaseType.FINISHTURNPHASE;
    }


    /**
     *Tries to initialize the passing turn main method.
     *
     */
    private void executePassingTurn(){
        try {
            controller.tryToSelectNewTurnKind(TurnType.PASSING);
            controller.getCurrentTurn().main();
        }catch (Exception e){e.printStackTrace();}

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

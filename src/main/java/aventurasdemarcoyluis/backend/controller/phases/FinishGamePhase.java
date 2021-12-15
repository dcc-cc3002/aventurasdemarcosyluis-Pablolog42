package aventurasdemarcoyluis.backend.controller.phases;

import aventurasdemarcoyluis.backend.controller.GameController;

/**
 * Class depicting the final phase of the game.
 * Part of the Phases' "State" design patter implementation.
 */
public class FinishGamePhase extends Phase{

    PhaseType phaseType = PhaseType.FINISHGAMEPHASE;

    /**
     * finish game constructor
     * @param controller the game's controller
     */
    public FinishGamePhase(GameController controller) {
        super(controller);
    }

    /**
     * Try to transition to next phase, according to the current
     * phase change prerequisites.
     *
     * @param phase The new phase to try to transition to.
     */
    @Override
    public void toNextPhase(Phase phase) {}

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        // The game finish phase doesn't admit any change, as the game is finished
        return false;
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


    /**
     * String Representation of the phase
     * @return String Representation of the phase
     */
    @Override
    public String toString() {
        return "FinishGamePhase";
    }
}

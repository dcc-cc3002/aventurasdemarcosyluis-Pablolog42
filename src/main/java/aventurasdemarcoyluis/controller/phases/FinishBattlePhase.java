package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.ItemType;

public class FinishBattlePhase extends Phase {

    PhaseType phaseType = PhaseType.FINISHBATTLEPHASE;

    public FinishBattlePhase(GameController controller) {
        super(controller);
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
        boolean r1 = phaseToBeChanged.getType() == PhaseType.FINISHGAMEPHASE;
        boolean r2 = phaseToBeChanged.getType() == PhaseType.STARTBATTLEPHASE;
        return r1 || r2;
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

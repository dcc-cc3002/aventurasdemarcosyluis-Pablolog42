package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import org.jetbrains.annotations.NotNull;
/**
 * Class denoting the phase in which a type of phase to be played by a MainCharacter is selected.
 * Part of the Phases' "State" design patter implementation.
 */
public class WaitSelectTurnTypePhase extends Phase {

    PhaseType phaseType = PhaseType.WAITSELECTTURNTYPEPHASE;

    boolean isTurnTypeSelected = false;

    /**
     * WaitSelectTurnTypePhase constructor.
     * @param controller the game controller
     */
    public WaitSelectTurnTypePhase(GameController controller) {
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
        } catch (InvalidTransitionException e){ e.printStackTrace(); }
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(@NotNull Phase phaseToBeChanged) {
        boolean r1 = phaseToBeChanged.getType() == PhaseType.WAITSELECTITEMPHASE;
        boolean r2 = phaseToBeChanged.getType() == PhaseType.WAITSELECTATTACKTYPEPHASE;
        boolean r3 = phaseToBeChanged.getType() == PhaseType.STARTPASSINGPHASE;
        // Check if the type is selected, and whether it is a valid type
        return isTurnTypeSelected && (r1 || r2 || r3);
    }

    //

    /**
     * Selects the turn kind to transition phase to
     * @param selection the turn kind to try and select
     */
    @Override
    public void selectTurnKind(TurnType selection){
        try {
            controller.tryToSelectNewTurnKind(selection);
            // As a turn has been successfully selected, this prerequisite is accomplished.
            isTurnTypeSelected = true;
            toSelectedTurnPhase();

        }catch (Exception e){ e.printStackTrace(); }

    }


    /**
     * Checks if the transition requirements are achieved, then transitions the phase of the game.
     */
    public void toSelectedTurnPhase(){

        assert isTurnTypeSelected;

        switch (controller.getCurrentTurn().getType()){
            case ITEM -> this.toNextPhase(new WaitSelectItemPhase(controller));
            case ATTACK -> this.toNextPhase(new WaitSelectAttackTypePhase(controller));
            case PASSING ->  this.toNextPhase(new StartPassingTurnPhase(controller));
        }
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
     * String rep. of the current phase
     * @return a String rep. of the current phase
     */
    @Override
    public String toString() {
        return "WaitSelectTurnTypePhase";
    }




}

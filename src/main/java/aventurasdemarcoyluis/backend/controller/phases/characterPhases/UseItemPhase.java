package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.model.items.ItemType;

/**
 * Class denoting the phase in which a selected item is used.
 * Part of the Phases' "State" design patter implementation.
 */
public class UseItemPhase extends Phase {

    PhaseType phaseType = PhaseType.USEITEMPHASE;

    ItemType itemToBeUsed;

    private boolean hasItemBeenUsed = false;

    /**
     * UseItemPhase constructor
     * @param controller the controller handling the game
     * @param selectedItem The item previously selected to be used.
     */
    public UseItemPhase(GameController controller, ItemType selectedItem){
        super(controller);
        this.itemToBeUsed = selectedItem;
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
        boolean r1 = phaseToBeChanged.getType() == PhaseType.FINISHTURNPHASE;
        return r1 && hasItemBeenUsed;
    }

    /**
     * Will try to use the selected item and try to transition to finish phase
     */
    public void useSelectedItem(){
        try {
            controller.getPlayer().tryToUseItem(this.itemToBeUsed, controller.getCurrentTurnMainCharacter());
        }catch (InvalidSelectionException e){e.printStackTrace(); return;}

        hasItemBeenUsed = true;
        toNextPhase(new FinishTurnPhase(controller));

    }


    /**
     * String representation of the current phase
     * @return  a String representation of the current phase
     */
    @Override
    public String toString() {
        return "UseItemPhase";
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

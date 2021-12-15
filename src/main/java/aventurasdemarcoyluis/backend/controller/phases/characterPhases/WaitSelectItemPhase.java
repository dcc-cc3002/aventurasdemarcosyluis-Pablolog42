package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.model.items.ItemType;

/**
 * Class denoting the phase in which a type of item to be used by a MainCharacter is selected.
 * Part of the Phases' "State" design patter implementation.
 */
public class WaitSelectItemPhase extends Phase {

    PhaseType phaseType = PhaseType.WAITSELECTITEMPHASE;

    ItemType selectedItem = null;

    public boolean itemSelected = false;

    /**
     * WaitSelectItemPhase Constructor
     * @param controller the games controller
     */
    public WaitSelectItemPhase(GameController controller){
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
        } catch (InvalidTransitionException e){ e.printStackTrace();}
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        boolean r1 = phaseToBeChanged.getType() == PhaseType.USEITEMPHASE;
        return itemSelected && r1;
    }


    /**
     * Tries to select a given item to be used, and afterwards transitions to the phase to use the given item.
     * @param type The type of the item to try to use
     */
    public void selectItem(ItemType type){
        try{
            this.selectedItem = controller.retrieveItemFromPlayerVault(type).getType();
            itemSelected = true;
        } catch (InvalidSelectionException e){e.printStackTrace(); return;}

        toNextPhase(new UseItemPhase(controller, this.selectedItem));
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
     * string rep. of the given pahse
     * @return a string rep. of the given pahse.
     */
    @Override
    public String toString() {
        return "WaitSelectItemPhase";
    }



}

package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.InterItem;
import aventurasdemarcoyluis.model.items.ItemType;

public class WaitSelectItemPhase extends Phase {

    PhaseType phaseType = PhaseType.WAITSELECTITEMPHASE;

    ItemType selectedItem = null;

    public boolean itemSelected = false;

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
        boolean r1 = phaseToBeChanged.getType() == PhaseType.USEITEMPHASE;
        return itemSelected && r1;
    }


    public void selectItem(ItemType type){
        try{
            this.selectedItem = controller.retrieveItemFromPlayerVault(type).getType();
        } catch (InvalidSelectionException e){
            e.printStackTrace();
            return;
        }
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


    @Override
    public String toString() {
        return "WaitSelectItemPhase";
    }



}

package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.turns.InterItemTurn;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.items.InterItem;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public class UseItemPhase extends Phase {

    PhaseType phaseType = PhaseType.USEITEMPHASE;

    ItemType itemToBeUsed;

    private boolean hasItemBeenUsed = false;


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

    }


    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        return false;
    }

    // will use item and try to transition to finish phase
    public void useSelectedItem(){
        try {
            controller.getPlayer().tryToUseItem(this.itemToBeUsed, controller.getCurrentTurnMainCharacter());
        }catch (InvalidSelectionException e){
            e.printStackTrace();
            return;
        }
        hasItemBeenUsed = true;
    }




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


    // Useless methods
    @Override
    public void battleSetUpRoutine() {

    }

    @Override
    public void selectTurnKind(TurnType selection) {

    }

    @Override
    public void toSelectedTurnPhase() {

    }

    @Override
    public void selectItem(ItemType type) {

    }

    @Override
    public void selectAttackTypePhase(AttackType attackType) {

    }
}

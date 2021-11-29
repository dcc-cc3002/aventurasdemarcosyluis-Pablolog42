package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.ItemType;
import org.jetbrains.annotations.NotNull;

public class WaitSelectTurnTypePhase extends Phase {

    PhaseType phaseType = PhaseType.WAITSELECTTURNTYPEPHASE;

    boolean isTurnTypeSelected = false;


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
    public boolean validatePhaseChange(@NotNull Phase phaseToBeChanged) {
        boolean r1 = phaseToBeChanged.getType() == PhaseType.WAITSELECTITEMPHASE;
        boolean r2 = phaseToBeChanged.getType() == PhaseType.WAITSELECTATTACKTYPEPHASE;
        boolean r3 = phaseToBeChanged.getType() == PhaseType.STARTPASSINGPHASE;
        // Check if the type is selected, and whether it is a valid type
        return isTurnTypeSelected && (r1 || r2 || r3);
    }

    // I can only transition to a new phase when I have selected the turn I want to play.
    public void selectTurnKind(TurnType selection){
        try {
            controller.tryToSelectNewTurnKind(selection);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        // As a turn has been successfully selected, this prerequisite is accomplished.
        isTurnTypeSelected = true;
    }

    // Checks if the transition requirements are achieved, then transitions the phase of the game.
    public void toSelectedTurnPhase(){
        switch (controller.getCurrentTurn().getType()){
            case ITEM -> this.toNextPhase(new WaitSelectItemPhase(controller));
            case ATTACK -> this.toNextPhase(new WaitSelectAttackTypePhase(controller));
            case ENEMY ->  this.toNextPhase(new StartPassingTurnPhase(controller));
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

    @Override
    public String toString() {
        return "SelectAttackTurnPhase";
    }



    // Useless Methods (State design patter says that in this phase, they should do nothing.)
    @Override
    public void battleSetUpRoutine() {}
    @Override
    public void selectItem(ItemType type) {}
    @Override
    public void useSelectedItem() {}
    @Override
    public void selectAttackTypePhase(AttackType attackType) {}
    @Override
    public void selectEnemyToBeAttacked(int enemyNumber) {}
    @Override
    public void selectRandomEnemyToMakeAttack() {}
    @Override
    public void selectRandomMainCharacterToBeAttacked() {}

}

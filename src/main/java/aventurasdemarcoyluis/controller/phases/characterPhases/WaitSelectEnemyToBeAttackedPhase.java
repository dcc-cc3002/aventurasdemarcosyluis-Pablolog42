package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTurnException;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.ItemType;

public class WaitSelectEnemyToBeAttackedPhase extends Phase {

    boolean enemyToBeAttackedSelected = false;

    PhaseType phaseType = PhaseType.WAITSELECTENEMYTOBEATTACKEDPHASE;


    public WaitSelectEnemyToBeAttackedPhase(GameController controller) {
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

    @Override
    public void selectEnemyToBeAttacked(int enemyNumber){
        try{
            controller.tryToSelectEnemyToBeAttacked(enemyNumber);
        } catch (InvalidTurnException e){
        e.printStackTrace();
        return;
        }
        enemyToBeAttackedSelected=true;
        // Automatically transition to the attack phase.
        toNextPhase(new AttackPhase(controller));
    }

    @Override
    public void selectRandomEnemyToMakeAttack() {

    }

    @Override
    public void selectRandomMainCharacterToBeAttacked() {

    }


    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        boolean r1 = phaseToBeChanged.getType() == PhaseType.ATTACKPHASE;
        return r1 && this.enemyToBeAttackedSelected;
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
    public void useSelectedItem() {

    }
    @Override
    public void selectAttackTypePhase(AttackType attackType) {

    }
}

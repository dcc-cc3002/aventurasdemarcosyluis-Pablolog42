package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.ItemType;

public class FinishGamePhase extends Phase{

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

    /**
     * Gets the type oh the current phase.
     *
     * @return The current phase's type
     */
    @Override
    public PhaseType getType() {
        return null;
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
    @Override
    public void selectEnemyToBeAttacked(int enemyNumber) {

    }
    @Override
    public void selectRandomEnemyToMakeAttack() {

    }
    @Override
    public void selectRandomMainCharacterToBeAttacked() {

    }
}

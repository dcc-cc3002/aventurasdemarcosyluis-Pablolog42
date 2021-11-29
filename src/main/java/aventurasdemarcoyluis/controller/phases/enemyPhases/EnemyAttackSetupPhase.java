package aventurasdemarcoyluis.controller.phases.enemyPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public class EnemyAttackSetupPhase extends Phase {

    PhaseType phaseType = PhaseType.ENEMYATTACKSETUPPHASE;

    boolean enemyToMakeAttackSelected=false;
    boolean mainCharacterToBeAttackedSelected = false;

    InterMainCharacter mainCharacterToBeAttacked = null;
    InterEnemy enemyToPerformTheAttack = null;

    public EnemyAttackSetupPhase(GameController controller) {
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
        boolean r1 = phaseToBeChanged.getType() == PhaseType.ENEMYATTACKPHASE;
        return enemyToMakeAttackSelected && mainCharacterToBeAttackedSelected && r1;
    }

    @Override
    public void selectRandomEnemyToMakeAttack(){
        try {
            this.enemyToPerformTheAttack = controller.getEnemyList().retrieveRandomEnemy();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void selectRandomMainCharacterToBeAttacked(){
        try {
            mainCharacterToBeAttacked =  controller.getRandomMainCharacterFromActiveList();
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        this.mainCharacterToBeAttackedSelected=true;
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

    // Ignored Methods.

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
}

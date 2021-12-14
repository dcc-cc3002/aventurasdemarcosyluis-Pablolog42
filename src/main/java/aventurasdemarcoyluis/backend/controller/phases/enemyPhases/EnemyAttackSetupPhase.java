package aventurasdemarcoyluis.backend.controller.phases.enemyPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

public class EnemyAttackSetupPhase extends Phase {

    PhaseType phaseType = PhaseType.ENEMYATTACKSETUPPHASE;

    boolean enemyToMakeAttackSelected=false;
    boolean mainCharacterToBeAttackedSelected = false;

    InterMainCharacter mainCharacterToBeAttacked = null;
    InterEnemy enemyToPerformTheAttack = null;

    public EnemyAttackSetupPhase(GameController controller) {
        super(controller);

        selectRandomEnemyToMakeAttack();
        selectRandomMainCharacterToBeAttacked();
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
        enemyToMakeAttackSelected = true;
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


    @Override
    public String toString(){
        return "EnemyAttackSetupPhase";
    }

}

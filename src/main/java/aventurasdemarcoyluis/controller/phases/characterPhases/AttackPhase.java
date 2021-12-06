package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.turns.AttackTurn;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.InterEntity;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public class AttackPhase extends Phase {

    PhaseType phaseType = PhaseType.ATTACKPHASE;

    InterEnemy enemyToABeAttacked;
    InterMainCharacter mainCharacterToAttack;

    boolean attackCompleted = false;

    public AttackPhase(InterMainCharacter mainCharacterToAttack, GameController controller) {
        super(controller);
        this.mainCharacterToAttack=mainCharacterToAttack;

        // Automatically attacks the selected enemy, with the selected attack.
        performAttack();

        toNextPhase(evaluateNextPhase());
    }

    /**
     * Evalua a que fase se deberia cambiar, acorde a los estados KO de los enemigos y aliados.
     * @return
     */
    private Phase evaluateNextPhase() {
        return new FinishTurnPhase(controller);
    }

    @Override
    public String toString() {
        return "AttackPhase";
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
        // Either the turn ends, or the battle ends as both mainCharacters could result KO after this phase
        boolean r1 = phaseToBeChanged.getType() == PhaseType.FINISHTURNPHASE;
        boolean r2 = phaseToBeChanged.getType() == PhaseType.FINISHBATTLEPHASE;
        return attackCompleted && (r1 || r2);
    }


    public void performAttack(){
        AttackTurn turn = (AttackTurn) controller.getCurrentTurn();
        try {
            turn.main();
        }catch (Exception e){
            e.printStackTrace();
        }
        attackCompleted = true;
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

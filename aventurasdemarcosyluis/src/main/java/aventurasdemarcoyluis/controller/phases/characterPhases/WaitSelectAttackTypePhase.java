package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.turns.AttackTurn;
import aventurasdemarcoyluis.model.AttackType;

public class WaitSelectAttackTypePhase extends Phase {


    PhaseType phaseType = PhaseType.WAITSELECTATTACKTYPEPHASE;

    boolean attackSelected = false;



    public WaitSelectAttackTypePhase(GameController controller) {
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
        return phaseToBeChanged.getType() == PhaseType.WAITSELECTENEMYTOBEATTACKEDPHASE;
    }


    @Override
    public void selectAttackType(AttackType attackType){
        try{
            tryToSelectAttack(attackType);
        }catch (InvalidAttackException e){
            e.printStackTrace();
            return;
        }
        attackSelected = true;
        // As soon as a valid attack is selected, try to go on to the attackPhase.
        toNextPhase(new WaitSelectEnemyToBeAttackedPhase(this.controller));
    }


    private void tryToSelectAttack(AttackType attackType) throws InvalidAttackException {
        // throws exceptions if no FP left. or if character KO.
        controller.getCurrentTurnMainCharacter().validateAttack(attackType);

        AttackTurn turn = (AttackTurn) controller.getCurrentTurn();
        turn.setAttackType(attackType);

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
        return "WaitSelectAttackTypePhase";
    }
}

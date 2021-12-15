package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.turns.AttackTurn;
import aventurasdemarcoyluis.backend.model.AttackType;

/**
 * Class denoting the phase in which a type of attack to be performed by a mainCharacter is selected.
 * Part of the Phases' "State" design patter implementation.
 */
public class WaitSelectAttackTypePhase extends Phase {


    PhaseType phaseType = PhaseType.WAITSELECTATTACKTYPEPHASE;

    boolean attackSelected = false;


    /**
     * WaitSelectAttackTypePhase constructor.
     * @param controller The controller Handling the game.
     */
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
        } catch (InvalidTransitionException e){e.printStackTrace();}
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

    /**
     * Tries to select a given attack Type to be performed in the next phase.
     * @param attackType The attack type to try to select
     */
    @Override
    public void selectAttackType(AttackType attackType){
        try{
            // throws exceptions if no FP left. or if character KO.
            controller.getCurrentTurnMainCharacter().validateAttack(attackType);

            AttackTurn turn = (AttackTurn) controller.getCurrentTurn();
            turn.setAttackType(attackType);
        }catch (InvalidAttackException e){e.printStackTrace();return;}
        attackSelected = true;
        // As soon as a valid attack is selected, try to go on to the attackPhase.
        toNextPhase(new WaitSelectEnemyToBeAttackedPhase(this.controller));
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
     * A string representation of the current phase
     * @return  A string representation of the current phase
     */
    @Override
    public String toString() {
        return "WaitSelectAttackTypePhase";
    }
}

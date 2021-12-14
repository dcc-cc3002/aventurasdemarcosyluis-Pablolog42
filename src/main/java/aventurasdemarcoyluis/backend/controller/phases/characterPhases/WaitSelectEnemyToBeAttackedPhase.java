package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.EnemyList;
import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.turns.AttackTurn;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;

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
    public void selectEnemyToBeAttacked(int enemyNumber) throws InvalidSelectionException {
        try{
           tryToSelectEnemyToBeAttacked(enemyNumber);
        } catch (InvalidSelectionException e){
        e.printStackTrace();
        return;
        }
        enemyToBeAttackedSelected=true;
        // Automatically transition to the attack phase.
        toNextPhase(new AttackPhase(controller.getCurrentTurnMainCharacter(), controller));


        //now, we operate in the attackphase, and set all the necesary methods..
        controller.getCurrentPhase().performAttack();
        controller.getCurrentPhase().toNextPhase(controller.getCurrentPhase().evaluateNextPhase());
    }


    @Override
    public String toString(){
        return "WaitSelectEnemyToBeAttackedPhase";
    }


    public void tryToSelectEnemyToBeAttacked(int enemyIndex) throws InvalidSelectionException {
        if(!(controller.getCurrentTurn().getType() == TurnType.ATTACK)) throw new InvalidSelectionException("Can't select enemy in the current turn state");

        AttackTurn turn = (AttackTurn) controller.getCurrentTurn();
        turn.setEnemyNumberToAttack(enemyIndex);
    }


    private EnemyList getCurrentEnemyList(){
        return controller.getEnemyList();
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


}

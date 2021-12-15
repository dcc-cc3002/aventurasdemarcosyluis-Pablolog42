package aventurasdemarcoyluis.backend.controller.phases.characterPhases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.backend.controller.phases.Phase;
import aventurasdemarcoyluis.backend.controller.phases.PhaseType;
import aventurasdemarcoyluis.backend.controller.turns.AttackTurn;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

/**
 * Class denoting an AttackPhase phase.
 * Part of the Phases' "State" design patter implementation.
 */
public class AttackPhase extends Phase {

    PhaseType phaseType = PhaseType.ATTACKPHASE;

    InterMainCharacter mainCharacterToAttack;

    boolean attackCompleted = false;

    /**
     * Attack Phase constrictor.
     * @param mainCharacterToAttack The main character to perform the attack.
     * @param controller the game controller of the phase and turn.
     */
    public AttackPhase(InterMainCharacter mainCharacterToAttack, GameController controller) {
        super(controller);
        this.mainCharacterToAttack=mainCharacterToAttack;
    }

    /**
     * Evalua a que fase se deberia cambiar, acorde a los estados KO de los enemigos y aliados.
     * @return
     */
    @Override
    public Phase evaluateNextPhase() {
        return new FinishTurnPhase(controller);
    }

    /**
     * String representation of the phase.
     * @return the string representing an attack phase.
     */
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
        // Either the turn ends, or the battle ends as both mainCharacters could result KO after this phase
        boolean r1 = phaseToBeChanged.getType() == PhaseType.FINISHTURNPHASE;
        boolean r2 = phaseToBeChanged.getType() == PhaseType.FINISHBATTLEPHASE;
        return attackCompleted && (r1 || r2);
    }

    /**
     *Tries to initialize the attack turn, given the parameters selected on the current attack phase.
     * Sets the attackCompleted requisite to true.
     */
    @Override
    public void performAttack(){
        AttackTurn turn = (AttackTurn) controller.getCurrentTurn();
        try {
            turn.main();
        }catch (Exception e){ e.printStackTrace(); }
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

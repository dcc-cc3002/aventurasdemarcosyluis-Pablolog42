package aventurasdemarcoyluis.backend.controller.phases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.items.ItemType;


/**
 * Class depicting an abstract Phase.
 * It's the heart of the Phase's "State" design pattern.
 */
public abstract class Phase implements InterPhase{

    protected GameController controller;
    PhaseType phaseType= null;

    /**
     * Abstract Phase Constructor Class
     * @param controller the game's controller.
     */
    public Phase(GameController controller){
        this.controller = controller;
    }

    /**
     * Set's the phase controller
     * @param controller the controller to be set
     */
    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Try to transition to next phase, according to the current
     * phase change prerequisites.
     * @param phase The new phase to try to transition to.
     */
    public void toNextPhase(Phase phase) {
        // Does nothing in the current state.
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        return true;
    }

    /**
     * String rep. of a phase.
     * @return String rep. of a phase.
     */
    @Override
    public String toString() {
        return "Phase";
    }

    // Start Battle Phase

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void battleSetUpRoutine()  {
        // Does nothing in the current state.
    };


    // WaitSelectTurnTypePhase

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void selectTurnKind(TurnType selection) throws InvalidSelectionException {
        throw new InvalidSelectionException("A turn kind can't be selected at the current " + phaseType + " phase.");
    }

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void toSelectedTurnPhase() throws InvalidSelectionException {
        throw new InvalidSelectionException("A turn phase can't be selected at the current " + phaseType + " phase.");

    }


    // Item Turn
    // WaitSelectItemPhase

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void selectItem(ItemType type) throws InvalidSelectionException {
        throw new InvalidSelectionException("An item can't be selected at the current " + phaseType + " phase.");

    }

    // UseItemPhase

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void useSelectedItem() throws InvalidSelectionException {
        throw new InvalidSelectionException("An item can't be used at the current " + phaseType + " phase.");
    }

    // Attack Turn

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void selectAttackType(AttackType attackType) throws InvalidSelectionException {
        throw new InvalidSelectionException("An attack can't be selected at the current " + phaseType + " phase.");
    }

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void selectEnemyToBeAttacked(int enemyNumber) throws InvalidSelectionException {
        throw new InvalidSelectionException("An enemy to attack can't be selected at the current " + phaseType + " phase.");
    }


    // finish main character Turn


    // Enemy Turn

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void selectRandomEnemyToMakeAttack() throws InvalidSelectionException {
        throw new InvalidSelectionException("An enemy to be the attacker be selected at the current " + phaseType + " phase.");
    }

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void selectRandomMainCharacterToBeAttacked() throws InvalidSelectionException {
        throw new InvalidSelectionException("A main character to be attacked can't be selected at the current " + phaseType + " phase.");
    }


    // Finish turn

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public Phase calculateNextPhaseAfterTurnFinished() throws InvalidSelectionException{
        throw new InvalidSelectionException("The next phase can't be calculated at the current " + phaseType + " phase.");
    }

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public Phase evaluateNextPhase() throws InvalidSelectionException {
        throw new InvalidSelectionException("The next phase can't be calculated at the current " + phaseType + " phase.");
    }

    /**
     * State-depending method. In the current Abstract Phase, does nothing/ Throws an invalid selection exception if called.
     * Part of the State design patter implementation.
     */
    public void performAttack() throws InvalidSelectionException {
        throw new InvalidSelectionException("Can't perform attack at the current " + phaseType + " phase.");
    }

    /**
     * Gets the type of the current turn
     * @return the current turns' type
     */
    @Override
    public PhaseType getType() {
        return phaseType;
    }
}

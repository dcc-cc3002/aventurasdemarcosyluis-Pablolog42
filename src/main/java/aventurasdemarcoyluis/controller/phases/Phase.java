package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;
import aventurasdemarcoyluis.model.items.ItemType;

public abstract class Phase implements InterPhase{

    protected GameController controller;
    PhaseType phaseType= null;


    public Phase(GameController controller){
        this.controller = controller;
    }


    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void toNextPhase(Phase phase) {
        try {
            controller.tryToChangePhase(phase);
        } catch (InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    // This method should be implemented by every class.
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        return true;
    }

    @Override
    public String toString() {
        return "Phase";
    }

    // Start Battle Phase
    public void battleSetUpRoutine() throws InvalidSelectionException {
        throw new InvalidSelectionException("A battle can't be set up at " + phaseType + " phase.");
    };


    // WaitSelectTurnTypePhase
    public void selectTurnKind(TurnType selection) throws InvalidSelectionException {
        throw new InvalidSelectionException("A turn kind can't be selected at the current " + phaseType + " phase.");
    }

    public void toSelectedTurnPhase() throws InvalidSelectionException {
        throw new InvalidSelectionException("A turn phase can't be selected at the current " + phaseType + " phase.");

    }


    // Item Turn
    // WaitSelectItemPhase
    public void selectItem(ItemType type) throws InvalidSelectionException {
        throw new InvalidSelectionException("An item can't be selected at the current " + phaseType + " phase.");

    }

    // UseItemPhase
    public void useSelectedItem() throws InvalidSelectionException {
        throw new InvalidSelectionException("An item can't be used at the current " + phaseType + " phase.");
    }

    // Attack Turn
    public void selectAttackType(AttackType attackType) throws InvalidSelectionException {
        throw new InvalidSelectionException("An attack can't be selected at the current " + phaseType + " phase.");
    }

    public void selectEnemyToBeAttacked(int enemyNumber) throws InvalidSelectionException {
        throw new InvalidSelectionException("An enemy to attack can't be selected at the current " + phaseType + " phase.");
    }


    // finish main character Turn


    // Enemy Turn
    public void selectRandomEnemyToMakeAttack() throws InvalidSelectionException {
        throw new InvalidSelectionException("An enemy to be the attacker be selected at the current " + phaseType + " phase.");
    }

    public void selectRandomMainCharacterToBeAttacked() throws InvalidSelectionException {
        throw new InvalidSelectionException("A main character to be attacked can't be selected at the current " + phaseType + " phase.");
    }


    // Finish turn
    public PhaseType calculateNextPhaseAfterTurnFinished() throws InvalidSelectionException{
        throw new InvalidSelectionException("The next phase can't be calculated at the current " + phaseType + " phase.");
    };

    public PhaseType getType() {
        return phaseType;
    }
}

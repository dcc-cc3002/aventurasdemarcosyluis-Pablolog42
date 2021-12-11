package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

/**
 * Class that depicts a turn in which the player passes. (does nothing)
 */
public class PassingTurn extends AbstractTurn implements InterTurn {

    private final GameController controller;
    private final TurnType type = TurnType.PASSING;
    private final InterMainCharacter involvedMainCharacter = null;

    /**
     * PassingTurn Class constructor
     *
     * @param controller the controller that manages the current turn and battle.
     */
    public PassingTurn(GameController controller) {
        super(controller);
        this.controller = controller;
    }

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     * <p>
     * In this case, a passing turn does nothing.
     **/
    @Override
    public void main() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {
        System.out.println(this.controller.getPlayer().getPlayerName() + " has passed this turn. It's now the enemy's turn.");
        System.out.println();

    }


    /**
     * Returns the type of turn played.
     *
     * @return Type of turn played.
     */
    @Override
    public TurnType getType() {
        return this.type;
    }

    /**
     * Gets the current turn's "Involved Character"
     * <p>
     * The involved character is the mainCharacter of the player which is being currently
     * acted upon (either by using an item on them, or letting them attack an enemy).
     * <p>
     * Note that in the "Passing" turn, there is no action being performed, and thus,
     * the Involved Character should return null.
     *
     * @return The current Involved Character.
     */
    @Override
    public InterMainCharacter getInvolvedMainCharacter() {
        return null;
    }




}

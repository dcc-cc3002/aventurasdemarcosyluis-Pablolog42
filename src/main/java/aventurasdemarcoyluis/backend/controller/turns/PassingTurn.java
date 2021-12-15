package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

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




}

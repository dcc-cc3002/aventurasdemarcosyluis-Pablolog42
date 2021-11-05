package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;

public class PassingTurn implements InterTurn{

    private Player player;
    private TurnType type;

    public PassingTurn(Player player) {
        this.player = player;
        this.type = TurnType.PASSING;
    }

    /**
     *  Main method of the current turn.
     *  Implement's the logic chain of events according to the turn type.
     *
     *  In this case, a passing turn does nothing.
     **/
    @Override
    public void main() {
        System.out.println(this.player.getPlayerName() + " has passed this turn. It's now the enemy's turn.");
        System.out.println();

    }

    @Override
    public TurnType getType() {
        return type;
    }
}

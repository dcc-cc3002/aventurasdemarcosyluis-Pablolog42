package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;

public class AttackTurn implements InterTurn{

    private Player player;

    public AttackTurn(Player player) {
        this.player = player;
    }


    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     **/
    @Override
    public void main() {

    }
}

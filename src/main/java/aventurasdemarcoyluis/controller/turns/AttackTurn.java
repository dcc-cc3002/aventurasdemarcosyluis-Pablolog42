package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;

import java.util.Scanner;

public class AttackTurn implements InterTurn{

    private Player player;
    private TurnType type;

    public AttackTurn(Player player) {
        this.player = player;
        this.type = TurnType.ATTACK;
    }

    Scanner entrada = new Scanner(System.in);

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     **/
    @Override
    public void main() {

        System.out.println("####### You are now attacking! #######");


    }

    @Override
    public TurnType getType() {
        return type;
    }
}

package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.Scanner;

public class AttackTurn implements InterTurn{

    private Player player;

    public AttackTurn(Player player) {
        this.player = player;
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




}

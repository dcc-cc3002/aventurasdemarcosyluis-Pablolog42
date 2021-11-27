package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;

public class Example {
    public static void main(String[] args) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {


        GameController controller = new GameController();

        controller.setPlayer("Pablo");
        controller.createAndSetNewBattle();


        controller.startCurrentTurn();




    }
}

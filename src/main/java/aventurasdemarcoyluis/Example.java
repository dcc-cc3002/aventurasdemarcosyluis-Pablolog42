package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.Phase;

public class Example {



    public static void main(String[] args) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {


        GameController controller = new GameController();
        controller.setPlayer("Pablo");


        Phase currentPhase = controller.getCurrentPhase();












    }
}

package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.InterPhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.phases.characterPhases.AttackPhase;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.controller.turns.TurnType;

public class Example {



    public static void main(String[] args) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {


        GameController controller = new GameController();
        controller.setPlayer("Pablo");


        System.out.println(controller.getCurrentPhaseType());

        controller.getCurrentPhase().battleSetUpRoutine();

        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));

        controller.getCurrentPhase().selectTurnKind(TurnType.ITEM);

//        controller.getCurrentPhase().toNextPhase(new AttackPhase(controller));

        System.out.println(controller.getCurrentPhaseType());















    }
}

package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.characterPhases.StartPassingTurnPhase;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.AttackType;

public class Example {



    public static void main(String[] args) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {


        GameController controller = new GameController();
        controller.setPlayer("Pablo");



        System.out.println(controller.getCurrentPhase().getType());

        controller.toNextPhase(new WaitSelectTurnTypePhase(controller));

        System.out.println(controller.getCurrentPhase().getType());

        controller.getCurrentPhase().selectTurnKind(TurnType.PASSING);

        controller.getCurrentPhase().toNextPhase(new StartPassingTurnPhase(controller));

        System.out.println(controller.getCurrentPhase().getType());
//        System.out.print(controller.getCurrentTurn().toString());

        controller.getCurrentPhase().toNextPhase(new FinishTurnPhase(controller));

        System.out.println(controller.getCurrentPhase().getType());

        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));

        System.out.println(controller.getCurrentPhase().getType());

        controller.getCurrentPhase().selectTurnKind(TurnType.ATTACK);

        controller.getCurrentPhase().selectAttackType(AttackType.JUMP);

        controller.getCurrentPhase().selectEnemyToBeAttacked(1);

        // The attack is performed intermediately after selecting validly an attack type, and which enemy to attack.
        System.out.println(controller.getCurrentPhase().getType());

        controller.getCurrentPhase().toNextPhase(new FinishTurnPhase(controller));

        System.out.println(controller.getCurrentPhase().getType());

        controller.getCurrentPhase().toNextPhase();









    }
}

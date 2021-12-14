import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.phases.*;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.*;
import aventurasdemarcoyluis.backend.controller.phases.enemyPhases.EnemyAttackPhase;
import aventurasdemarcoyluis.backend.controller.phases.enemyPhases.EnemyAttackSetupPhase;
import aventurasdemarcoyluis.backend.controller.turns.TurnType;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhase {

    GameController controller;

    @BeforeEach
    public void setUp(){
        controller = new GameController("Ernesto Sábato");
    }


    @Test
    public void phaseOrderTest() throws InvalidSelectionException {
        // Se testea que el orden de las fases sea el correcto, dado uno de los posibles caminos de juego.
        // Se escoge el primer camino de juego, acorde a lo especigicado en los archivos de las notas de implementacion
        controller.runFirstBattle();
        assertTrue(currentPhaseEquals(PhaseType.STARTBATTLEPHASE));
        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));
        assertTrue(currentPhaseEquals(PhaseType.WAITSELECTTURNTYPEPHASE));
        controller.getCurrentPhase().selectTurnKind(TurnType.ITEM);
        assertTrue(currentPhaseEquals(PhaseType.WAITSELECTITEMPHASE));

        // The phase change logic is directly implemented onto the selectItem method in the WaitSelectItemPhase class.
        controller.getCurrentPhase().selectItem(ItemType.REDMUSHROOM);
        assertTrue(currentPhaseEquals(PhaseType.USEITEMPHASE));
        // We use the item
        controller.getCurrentPhase().useSelectedItem();

        // after the items has been used, the controller immediately transitions to a finish turn phase.
        assertTrue(currentPhaseEquals(PhaseType.FINISHTURNPHASE));



    }


    /**
     * Requisito 1:
     * Definir tests en los que se decida arbitrariamente usar un item
     */
    @Test
    public void arbitraryItemPhase() throws InvalidSelectionException {
        controller.runFirstBattle();
        assertTrue(currentPhaseEquals(PhaseType.STARTBATTLEPHASE));
        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));
        assertTrue(currentPhaseEquals(PhaseType.WAITSELECTTURNTYPEPHASE));
        controller.getCurrentPhase().selectTurnKind(TurnType.ITEM);
        assertTrue(currentPhaseEquals(PhaseType.WAITSELECTITEMPHASE));


    }

    @Test
    public void arbitraryAttackPhase() throws InvalidSelectionException{
        // Inicio de Turno
        controller.runFirstBattle();

        // The user arbitrarily selects an attack turn.
        controller.getCurrentPhase().toNextPhase(new WaitSelectTurnTypePhase(controller));

        controller.getCurrentPhase().selectTurnKind(TurnType.ATTACK);


        // Once the user has selected the attack turn type, the controller will automatically
        // select and deploy the waitSelectAttackTypePhase, and wil start to listen for which attack the user wants to perform.
        controller.getCurrentPhase().selectAttackType(AttackType.JUMP);

        // once the user has selected the attack, the controller will wait for
        // the user to select wich enemy to attack.
        assertEquals(PhaseType.WAITSELECTENEMYTOBEATTACKEDPHASE, controller.getCurrentPhase().getType());


        // We attack the first enemy with marco.
        controller.getCurrentPhase().selectEnemyToBeAttacked(1);

        // The controller automatically performs the requested attack, and finishes the turn by setting the new phase.
        // to a FinishTurnPhase()
        assertEquals(PhaseType.FINISHTURNPHASE,controller.getCurrentPhase().getType());

        controller.getCurrentPhase().toNextPhase(new EnemyAttackSetupPhase(controller));


        controller.getCurrentPhase().toNextPhase(new EnemyAttackPhase(controller));

        controller.getCurrentPhase().toNextPhase(new FinishTurnPhase(controller));


    }

    @Test
    public void phaseNameTest(){
        assertEquals("AttackPhase",new AttackPhase(null,controller).toString());
        assertEquals("UseItemPhase",new UseItemPhase(controller,null).toString());
        assertEquals("WaitSelectAttackTypePhase",new WaitSelectAttackTypePhase(controller).toString());
        assertEquals("WaitSelectEnemyToBeAttackedPhase",new WaitSelectEnemyToBeAttackedPhase(controller).toString());
        assertEquals("WaitSelectItemPhase",new WaitSelectItemPhase(controller).toString());
        assertEquals("WaitSelectTurnTypePhase",new WaitSelectTurnTypePhase(controller).toString());
        assertEquals("FinishGamePhase",new FinishGamePhase(controller).toString());
        assertEquals("FinishTurnPhase",new FinishTurnPhase(controller).toString());
        assertEquals("StartBattlePhase",new StartBattlePhase(controller).toString());

    }


    public boolean currentPhaseEquals(PhaseType phaseType){
        return phaseType == this.controller.getCurrentPhase().getType();
    }

}

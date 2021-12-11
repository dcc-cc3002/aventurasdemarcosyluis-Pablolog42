import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.phases.PhaseType;
import aventurasdemarcoyluis.controller.phases.characterPhases.UseItemPhase;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectItemPhase;
import aventurasdemarcoyluis.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.items.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void invalidTransitionTest(){

    }


    public boolean currentPhaseEquals(PhaseType phaseType){
        return phaseType == this.controller.getCurrentPhase().getType();
    }

}

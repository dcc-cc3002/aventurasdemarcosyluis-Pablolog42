package TestController.unitTest;
import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentTurnTest {
    GameController controller;

    @BeforeEach
    public void setUp() {
        controller = new GameController();
        controller.setPlayer("Ernesto Sábato");
    }
    @Test
    public void getControllerFromTurnTest() throws InvalidSelectionException {
        controller.createAndSetNewBattle();
        controller.selectNewTurnKind(TurnType.ATTACK);
        // The controller should be the exact same.
        assertEquals(controller,controller.getCurrentTurn().getController());
    }


    /**
     * Testea funcionalidad número 9:
     * 9. Quitar a un personaje del turno cuando está KO.
     */
    @Test
    public void getCurrentTurnMainCharatersTest() throws IOException, InvalidSelectionException {
        controller.createAndSetNewBattle();
        controller.selectNewTurnKind(TurnType.ATTACK);

        ArrayList<InterMainCharacter> turnMainCharacterList = controller.getCurrentTurn().getCurrentTurnMainCharacters();

        //There should be 1 marco and 1 luis in the current turn



        // Lets murder luis
        this.controller.getPlayer().getLuis().setKO(true);

        // lets check the current turn characters again.
        turnMainCharacterList = controller.getCurrentTurn().getCurrentTurnMainCharacters();

        // There should only be one mario in the list, as the KO character is removed fronm the turn.
        // Luis and mario should be in the current turn
        assertEquals(1,turnMainCharacterList.size());
        for(InterMainCharacter character : turnMainCharacterList){
            assertTrue(character instanceof Marco);
        }



    }


}

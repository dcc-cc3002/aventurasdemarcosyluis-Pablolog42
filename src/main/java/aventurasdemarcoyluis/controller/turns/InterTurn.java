package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
// TODO: comentar interface
public interface InterTurn {

    /**
     *  Main method of the current turn.
     *  Implement's the logic chain of events according to the turn type.
     **/
    void main() throws IOException;
    ArrayList<InterMainCharacter> getCurrentTurnMainCharaters();
    TurnType getType();
    void setReader(BufferedReader reader);
    BufferedReader getReader();

    GameController getController();

    InterMainCharacter getInvolvedMainCharacter();
}

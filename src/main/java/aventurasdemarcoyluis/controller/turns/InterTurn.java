package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;

public interface InterTurn {

    /**
     *  Main method of the current turn.
     *  Implement's the logic chain of events according to the turn type.
     **/
    void main();
    ArrayList<InterMainCharacter> getCurrentTurnMainCharaters();
    TurnType getType();

}

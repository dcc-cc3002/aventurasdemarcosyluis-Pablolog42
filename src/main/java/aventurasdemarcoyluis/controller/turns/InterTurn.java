package aventurasdemarcoyluis.controller.turns;

public interface InterTurn {

    /**
     *  Main method of the current turn.
     *  Implement's the logic chain of events according to the turn type.
     **/
    void main();
    TurnType getType();



}

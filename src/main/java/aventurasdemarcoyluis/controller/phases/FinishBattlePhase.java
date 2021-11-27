package aventurasdemarcoyluis.controller.phases;

public class FinishBattlePhase extends Phase {

    //After a battle finishes, either the player has won (in case n>=5)
    public void toFinishGamePhase(){
        controller.changePhase(new FinishGamePhase());
    }

    //
    public void toStartBattlePhase(){
        controller.changePhase((new StartBattlePhase()));
    }
}

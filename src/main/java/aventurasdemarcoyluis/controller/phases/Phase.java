package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;

public class Phase {
    protected GameController controller;

    protected boolean canUseItem;
    protected boolean canAddItem;
    protected boolean canAttack;
    protected boolean canStartNewTurn;
    protected boolean canTransitionTurn;
    protected boolean canTransitionPhase;


    public Phase(){
        canUseItem = false;
        canAddItem =false;
        canAttack = false;
        canStartNewTurn = false;
        canTransitionTurn = false;
    }



    public void setController(GameController controller) {
        this.controller = controller;
    }


    public boolean canTransitionTurn() {
        return canTransitionTurn;
    }

    public void setCanTransitionTurn(boolean canTransitionTurn) {
        this.canTransitionTurn = canTransitionTurn;
    }

    public boolean canTransitionPhase() {
        return canTransitionPhase;
    }

    public void setCanTransitionPhase(boolean canTransitionPhase) {
        this.canTransitionPhase = canTransitionPhase;
    }

    @Override
    public String toString() {
        return "Phase";
    }
}

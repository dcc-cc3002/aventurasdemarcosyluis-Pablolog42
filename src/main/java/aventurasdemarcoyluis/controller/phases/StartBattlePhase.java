package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.items.ItemType;

public class StartBattlePhase extends Phase{

    PhaseType phaseType = PhaseType.STARTBATTLEPHASE;

    // Phase transition requirements
    private boolean isBattleSetup = false;

    public StartBattlePhase(GameController controller){
        super(controller);
        // the setup routine is automatically executed
        battleSetUpRoutine();

    }

    /**
     * Try to transition to next phase, according to the current
     * phase change prerequisites.
     *
     * @param phase The new phase to try to transition to.
     */
    @Override
    public void toNextPhase(Phase phase) {
        try {
            controller.tryToChangePhase(phase);
        } catch (InvalidTransitionException e){
            e.printStackTrace();
        }
    }

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    @Override
    public boolean validatePhaseChange(Phase phaseToBeChanged) {
        // One prerequisite is to check if the battle is setup.
        boolean r1 = isBattleSetup;

        boolean r2 = phaseToBeChanged.getType() == PhaseType.WAITSELECTTURNTYPEPHASE;

        return r1&&r2;
    }

    /**
     * Gets the type oh the current phase.
     *
     * @return The current phase's type
     */
    @Override
    public PhaseType getType() {
        return this.phaseType;
    }

    @Override
    public void battleSetUpRoutine(){
        controller.createAndSetNewBattle();
        isBattleSetup = true;
    }


    // Useless methods

    @Override
    public void selectTurnKind(TurnType selection) {}

    @Override
    public void toSelectedTurnPhase() {}

    @Override
    public void selectItem(ItemType type) {}


}

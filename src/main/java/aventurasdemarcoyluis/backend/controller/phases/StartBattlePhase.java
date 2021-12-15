package aventurasdemarcoyluis.backend.controller.phases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;

/**
 * Class depicting the end of a battle.
 * Part of the Phases' "State" design patter implementation.
 */
public class StartBattlePhase extends Phase{

    PhaseType phaseType = PhaseType.STARTBATTLEPHASE;

    // Phase transition requirements
    private boolean isBattleSetup = false;

    /**
     * StartBattlePhase constructor
     * @param controller the game's controller
     */
    public StartBattlePhase(GameController controller){
        super(controller);
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
        } catch (InvalidTransitionException e){ e.printStackTrace(); }
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



    /**
     * Set's in motion the battle initial  routine.
     */
    @Override
    public void battleSetUpRoutine(){
        controller.createAndSetNewBattle();
        isBattleSetup = true;
    }

    /**
     * String Representation of the phase
     * @return String Representation of the phase
     */
    @Override
    public String toString() {
        return "StartBattlePhase";
    }
}

package aventurasdemarcoyluis.controller.phases;

import aventurasdemarcoyluis.controller.turns.TurnType;
import aventurasdemarcoyluis.model.items.ItemType;

public interface InterPhase {
    //TODO rellenar esto

    // All phases

    /**
     * Try to transition to next phase, according to the current
     * phase change prerequisites.
     * @param phase The new phase to try to transition to.
     */
    void toNextPhase(Phase phase);

    /**
     * Validates whether the current transition phase is legal.
     *
     * @param phaseToBeChanged The phase to check for transition validity.
     * @return The boolean indicating if the phase transition is valid or not.
     */
    boolean validatePhaseChange(Phase phaseToBeChanged);

    /**
     * Gets the type oh the current phase.
     * @return The current phase's type
     */
    PhaseType getType();

    ////////////////////////////////////////////////////



    // Start Battle Phase
    void battleSetUpRoutine();


    // WaitSelectTurnTypePhase
    void selectTurnKind(TurnType selection);
    void toSelectedTurnPhase();


    // Item Turn
        // WaitSelectItemPhase
        void selectItem(ItemType type);

    // Attack Turn

    // finish main character Turn


    // Enemy Turn


    // Battle won



    // cosas del futbol



}

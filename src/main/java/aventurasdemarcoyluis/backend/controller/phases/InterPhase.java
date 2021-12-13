package aventurasdemarcoyluis.backend.controller.phases;

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



    // Battle won



    // cosas del futbol



}

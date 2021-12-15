package aventurasdemarcoyluis.backend.controller.phases;

/**
 * Enum denoting all the different phase types, classified by phase order.
 */
public enum PhaseType {
    STARTBATTLEPHASE,
    WAITSELECTTURNTYPEPHASE,
        //ITEMPHASE
        WAITSELECTITEMPHASE,
            USEITEMPHASE,
        //ATTACKPHASE
        WAITSELECTATTACKTYPEPHASE,
            WAITSELECTENEMYTOBEATTACKEDPHASE,
                ATTACKPHASE,
        //PASSING PHASE
        STARTPASSINGPHASE,
    // fINISH TURNS
    FINISHTURNPHASE,

    // ENEMY PHASES
    ENEMYATTACKSETUPPHASE,
    ENEMYATTACKPHASE,

    FINISHBATTLEPHASE,
    FINISHGAMEPHASE



}

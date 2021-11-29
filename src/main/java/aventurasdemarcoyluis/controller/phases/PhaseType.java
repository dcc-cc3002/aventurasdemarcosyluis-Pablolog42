package aventurasdemarcoyluis.controller.phases;

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
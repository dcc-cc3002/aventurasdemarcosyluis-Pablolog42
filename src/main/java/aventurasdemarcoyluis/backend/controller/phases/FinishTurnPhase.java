package aventurasdemarcoyluis.backend.controller.phases;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.controller.phases.characterPhases.WaitSelectTurnTypePhase;
import aventurasdemarcoyluis.backend.controller.phases.enemyPhases.EnemyAttackSetupPhase;
import aventurasdemarcoyluis.backend.controller.turns.TurnOwner;
import aventurasdemarcoyluis.backend.model.EntityType;

public class FinishTurnPhase extends Phase{


    PhaseType phaseType = PhaseType.FINISHTURNPHASE;

    public FinishTurnPhase(GameController controller) {
        super(controller);
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
            controller.finishTurn();
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
        // In case the current main character is the last one on the list, go to the enemy turn.
        boolean r1 = phaseToBeChanged.getType() == PhaseType.ENEMYATTACKSETUPPHASE;
        // In case there is another main character before the enemy, change to the maincharacterSelectTurnType phase.
        boolean r2 = phaseToBeChanged.getType() == PhaseType.WAITSELECTTURNTYPEPHASE;
        // in case the player has lost and the game is over.
        boolean r3 = phaseToBeChanged.getType() == PhaseType.FINISHGAMEPHASE;
        // en caso de que no queden enemigos por pelear
        boolean r4 = phaseToBeChanged.getType() == PhaseType.FINISHBATTLEPHASE;

        return (r1 || r2 || r3 || r4);
    }


    // TODO: REVISAR SI ESTAN KO, REVISAR QUIEN ESTA KO Y SACARLO DEL TURNO, REVISAR AL PERSONAJE QUE QUEDE
    // Y CAMBIAR LA FASE ACORDE A LO QUE CORRESPONDA.
    @Override
    public Phase calculateNextPhaseAfterTurnFinished(){

        if(controller.getPlayer().isPlayerKO()){
            return new FinishGamePhase(controller);
        }

        // Si AMrco esta KO pero no Luis
        if(controller.getPlayer().getMarco().isKO()){
            try {
                controller.getActiveMainCharacterList().removeMainCharacterFromActiveList(EntityType.MARCO);
            }catch (Exception ignored){
                // Se ignora la exception, en caso de que el personaje ya se haya quitado del turno anteriormente.
            }
            // como todavia queda patria (un personaje vivo al menos), se sigue el juego

            // si el turno era de un personaje, pasarlo al enemigo.
            if(controller.getCurrentTurnOwner() == TurnOwner.MARCO || controller.getCurrentTurnOwner() == TurnOwner.LUIS ){
                return new EnemyAttackSetupPhase(controller);
            }

            if(controller.getCurrentTurnOwner() == TurnOwner.ENEMY ){
                return new WaitSelectTurnTypePhase(controller);
            }
        }

        // Si AMrco esta KO pero no Luis
        if(controller.getPlayer().getLuis().isKO()){
            try {
                controller.getActiveMainCharacterList().removeMainCharacterFromActiveList(EntityType.LUIS);
            }catch (Exception ignored){
                // Se ignora la exception, en caso de que el personaje ya se haya quitado del turno anteriormente.
            }
            // como todavia queda patria (un personaje vivo al menos), se sigue el juego

            // si el turno era de un personaje, pasarlo al enemigo.
            if(controller.getCurrentTurnOwner() == TurnOwner.MARCO || controller.getCurrentTurnOwner() == TurnOwner.LUIS ){
                return new EnemyAttackSetupPhase(controller);
            }

            if(controller.getCurrentTurnOwner() == TurnOwner.ENEMY ){
                return new WaitSelectTurnTypePhase(controller);
            }
        }
        // If no one is KO, continue with the current turn structure

        switch (controller.getNextTurnOwner()){
            case LUIS, MARCO -> {
                return new WaitSelectTurnTypePhase(controller);
            }
            case ENEMY -> {return new EnemyAttackSetupPhase(controller);}
        }
        return null;
    }


    /**
     * Gets the type oh the current phase.
     *
     * @return The current phase's type
     */
    @Override
    public PhaseType getType() {
        return phaseType;
    }

    @Override
    public String toString() {
        return "FinishTurnPhase";
    }


}

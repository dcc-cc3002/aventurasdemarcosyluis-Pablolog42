package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;

public class PassingTurn extends AbstractTurn implements InterTurn{

    private GameController controller;
    private final TurnType type = TurnType.PASSING;
    private InterMainCharacter involvedMainCharacter=null;

    public PassingTurn(GameController controller) {
        super(controller);
        this.controller = controller;
    }

    /**
     *  Main method of the current turn.
     *  Implement's the logic chain of events according to the turn type.
     *
     *  In this case, a passing turn does nothing.
     **/
    @Override
    public void main() {
        System.out.println(this.controller.getPlayer().getPlayerName() + " has passed this turn. It's now the enemy's turn.");
        System.out.println();

    }

    @Override
    public ArrayList<InterMainCharacter> getCurrentTurnMainCharaters() {
        ArrayList<InterMainCharacter> currentTurnMainCharacters = new ArrayList<>();

        // Agrego solo los personajes principales que no están KO.
        // este metodo es el que se encarga de cumplir con el requisito
        // "Quitar a un personaje del "Turno" cuando esté KO"
        for (InterMainCharacter character : this.controller.getPlayer().getMainCharacterArrayList()){
            if(!character.isKO()) currentTurnMainCharacters.add(character);
        }
        return currentTurnMainCharacters;

    }

    @Override
    public TurnType getType() {
        return this.type;
    }

    @Override
    public InterMainCharacter getInvolvedMainCharacter() {
        return involvedMainCharacter;
    }


}

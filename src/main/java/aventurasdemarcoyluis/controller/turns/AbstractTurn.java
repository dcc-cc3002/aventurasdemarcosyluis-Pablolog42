package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public abstract class AbstractTurn implements InterTurn {

    private GameController controller;
    private BufferedReader reader;

    public AbstractTurn(GameController controller) {
        this.controller = controller;
    }

    @Override
    public BufferedReader getReader() {
        return reader;
    }

    @Override
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public GameController getController() {
        return controller;
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


}

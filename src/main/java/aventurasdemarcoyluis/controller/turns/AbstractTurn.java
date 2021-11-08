package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.InputStreamReader;


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



}

package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public abstract class AbstractTurn implements InterTurn {

    private Player player;


    public AbstractTurn(Player player) {
        this.player = player;
    }

}

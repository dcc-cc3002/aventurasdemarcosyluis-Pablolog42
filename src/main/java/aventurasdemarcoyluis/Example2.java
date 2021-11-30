package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.EntityType;

public class Example2 {
    public static void main(String[] args) {
        GameController controller = new GameController();

        controller.getPlayerMainCharacter(EntityType.LUIS).setKO(true);

        controller.checkForKoCharactersInPlayerList();

    }
}

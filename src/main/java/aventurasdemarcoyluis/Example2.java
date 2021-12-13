package aventurasdemarcoyluis;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.model.EntityType;

public class Example2 {
    public static void main(String[] args) {
        GameController controller = new GameController();

        controller.getPlayerMainCharacter(EntityType.LUIS).setKO(true);

//        controller.checkForKoCharactersInPlayerList();

    }
}

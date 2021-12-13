package aventurasdemarcoyluis;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

public class Test3 {
    public static void main(String[] args) throws InvalidAttackException {

        GameController controller = new GameController();

        InterMainCharacter marco = controller.createMainCharacter(EntityType.MARCO,10,10,10,10,10,10,10);

        InterMainCharacter luis = controller.createMainCharacter(EntityType.LUIS,10,10,10,10,10,10,10);

        InterEnemy boo = controller.createEnemy(EntityType.BOO,10,10,10,10,10);


        System.out.println(marco + "\n" + boo);

        marco.receiveDamage(10000);

    }
}

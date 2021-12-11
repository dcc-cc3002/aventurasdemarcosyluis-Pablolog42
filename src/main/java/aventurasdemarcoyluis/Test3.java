package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.enemies.Boo;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.Luis;
import aventurasdemarcoyluis.model.maincharacters.Marco;

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

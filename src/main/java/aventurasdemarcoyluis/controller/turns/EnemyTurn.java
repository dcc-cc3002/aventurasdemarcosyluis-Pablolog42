package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;

public class EnemyTurn extends AbstractTurn implements InterTurn {

    private GameController controller;
    private InterMainCharacter involvedMainCharacter;
    private final TurnType type = TurnType.ENEMY;

    public EnemyTurn(GameController controller) {
        super(controller);
        this.controller = controller;
    }


    @Override
    public void main() {
        // A random character and a random enemy are selected.
        this.involvedMainCharacter = selectCharacter();
        InterEnemy attackingEnemy = this.controller.getPlayer().getEnemyList().retrieveRandomEnemy();

        System.out.println("::::::::::::::: Enemy Turn :::::::::::::::");
        System.out.println("Randomly, " + attackingEnemy + " attacks " + this.involvedMainCharacter.getType());
        // Fight! https://pbs.twimg.com/media/DTMfiQOU0AEWIYA.jpg
        attackingEnemy.attack(this.involvedMainCharacter);
        System.out.println("::::::::::::::: End of Enemy Turn ::::::::::::::");
    }




    // TODO: Esto es lo más feo que he visto en mucho tiempo. Hay que cambiarlo y pedir perdón por nuestros pecados.
    public InterMainCharacter selectCharacter() {

        if (this.controller.getPlayer().isPlayerKO()) {
            //TODO: agregar exception
            System.out.println("The player is KO and can't be attacked!");
            return null;
        }

        // Selects which player's character to attack
        double rand = Math.random();
        InterMainCharacter attackedCharacter = rand < 0.5 ? this.controller.getPlayer().getLuis() : this.controller.getPlayer().getMarco();

        // The player can't be KO
        if(attackedCharacter.isKO()){
            attackedCharacter = selectCharacter();
        }

        return attackedCharacter;
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

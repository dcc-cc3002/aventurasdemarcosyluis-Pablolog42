package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.AbstractMainCharacter;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;

public class EnemyTurn extends AbstractTurn implements InterTurn {

    private Player player;
    private TurnType type;

    public EnemyTurn(Player player) {
        super(player);
        this.type = TurnType.ENEMY;
    }


    @Override
    public void main() {
        // A random character and a random enemy are selected.
        InterMainCharacter attackedCharacter = selectCharacter();
        InterEnemy attackingEnemy = this.player.getEnemyList().retrieveRandomEnemy();

        System.out.println("::::::::::::::: Enemy Turn :::::::::::::::");
        System.out.println("Randomly, " + attackingEnemy + " attacks " + attackedCharacter.getType());
        // Fight! https://pbs.twimg.com/media/DTMfiQOU0AEWIYA.jpg
        attackingEnemy.attack(attackedCharacter);
        System.out.println("::::::::::::::: End of Enemy Turn ::::::::::::::");
    }

    @Override
    public ArrayList<InterMainCharacter> getCurrentTurnMainCharaters() {
        ArrayList<InterMainCharacter> currentTurnMainCharacters = new ArrayList<>();

        // Agrego solo los personajes principales que no están KO.
        // este metodo es el que se encarga de cumplir con el requisito
        // "Quitar a un personaje del "Turno" cuando esté KO"
        for (InterMainCharacter character : this.player.getMainCharacterArrayList()){
            if(!character.isKO()) currentTurnMainCharacters.add(character);
        }
        return currentTurnMainCharacters;

    }


    // TODO: Esto es lo más feo que he visto en mucho tiempo. Hay que cambiarlo y pedir perdón por nuestros pecados.
    public InterMainCharacter selectCharacter() {
        // Selects which player's character to attack
        double rand = Math.random();
        InterMainCharacter attackedCharacter = rand < 0.5 ? this.player.getLuis() : this.player.getMarco();

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



}

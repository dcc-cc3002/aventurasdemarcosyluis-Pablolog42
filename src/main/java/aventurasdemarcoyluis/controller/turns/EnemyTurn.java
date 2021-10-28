package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.entities.enemies.InterEnemy;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;

public class EnemyTurn implements InterTurn {

    private Player player;

    public EnemyTurn(Player player) {
        this.player = player;
    }


    @Override
    public void main() {
        // A random character and a random enemy are selected.
        AbstractMainCharacter attackedCharacter = selectCharacter();
        InterEnemy attackingEnemy = this.player.getEnemyList().retrieveRandomEnemy();

        // Fight! https://pbs.twimg.com/media/DTMfiQOU0AEWIYA.jpg
        attackingEnemy.attack(attackedCharacter);

    }






    // TODO: Esto es lo más feo que he visto en mucho tiempo. Hay que cambiarlo.
    public AbstractMainCharacter selectCharacter() {
        // Selects which player's character to attack
        double rand = Math.random();
        AbstractMainCharacter attackedCharacter = rand < 0.5 ? this.player.getLuis() : this.player.getMarco();

        // The player can't be KO
        if(attackedCharacter.isKO()){
            attackedCharacter = selectCharacter();
        }

        return attackedCharacter;
    }



}

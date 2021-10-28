package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;

public class EnemyTurn {

    private Player player;

    public EnemyTurn(Player player) {
        this.player = player;
    }

    public void main() {

        AbstractMainCharacter attackedCharacter = selectCharacter();




    }

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

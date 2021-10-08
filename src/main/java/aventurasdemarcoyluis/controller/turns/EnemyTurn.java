package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.Battle;
import aventurasdemarcoyluis.Player;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;

public class EnemyTurn extends Battle {

    private Player player;

    public EnemyTurn(Player player) {
        this.player = player;
    }



    public void main() {

        // Testing. With this on, selectcharacter() should never return Marco
        this.player.getMarco().setKO(true);
        this.player.getLuis().setKO(true);

        AbstractMainCharacter attackedCharacter = selectCharacter();
        // System.out.println(attackedCharacter);





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

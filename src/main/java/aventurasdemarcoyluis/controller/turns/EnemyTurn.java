package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Battle;
import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.entities.maincharacters.AbstractMainCharacter;

public class EnemyTurn extends Battle {

    private Player player;

    public EnemyTurn(Player player) {
        this.player = player;
    }

    @Override
    public void main(Player player) {


        AbstractMainCharacter attackedCharacter = selectCharacter();

        // Seleccionar n tipos random, acorde al numero de batalla que se está jugando
        int turnNo = this.player.getBattleNumber();
        switch (turnNo){
            case 0, 1 -> System.out.println("deberian de haber 3 enemigos aleatorios");
            case 2, 3 -> System.out.println("deberian de haber 5 enemigos aleatorios");
            case 4 -> System.out.println("deberian de haber 6 enemigos aleatorios");
        }







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

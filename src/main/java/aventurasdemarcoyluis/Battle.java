package aventurasdemarcoyluis;

import aventurasdemarcoyluis.controller.turns.AttackTurn;
import aventurasdemarcoyluis.controller.turns.EnemyTurn;
import aventurasdemarcoyluis.controller.turns.ItemTurn;
import aventurasdemarcoyluis.controller.turns.PassingTurn;

public class Battle {

    private Boolean battleFinished;

    public Battle(){
        this.battleFinished = false;
    }

    public void main(Player player) {

        while(!this.battleFinished) {
            AttackTurn attackTurn = new AttackTurn(player);
            ItemTurn itemTurn = new ItemTurn(player);
            PassingTurn passingTurn = new PassingTurn(player);

            EnemyTurn enemyTurn = new EnemyTurn(player);

            


            enemyTurn.main();
            this.battleFinished = true;

        }


    }


    protected void playerVictorySequence() {
        System.out.println("The Player has won. Congratulations!");
    }

    protected void enemyVictorySequence() {
        System.out.println("All players characters have been defeated. Evil will now reign.");
    }

}

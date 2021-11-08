package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AttackTurn extends AbstractTurn implements InterTurn {

    private GameController controller;
    private final TurnType type = TurnType.ATTACK;


    private InterMainCharacter involvedMainCharacter;
    private BufferedReader reader;

    /**
     * AttackTurn Constructor
     * By default, sets the class reader as a System.in BufferStream.
     * @param controller the game controller controlling the turn.
     */
    public AttackTurn(GameController controller) {
        super(controller);
        this.controller = controller;
        this.involvedMainCharacter = null;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     **/
    @Override
    public void main() throws IOException {

        System.out.println("####### You are now attacking! #######");
        System.out.println("Select the enemy to attack: (Choose a number)");
        System.out.println("Wild enemies:");


        System.out.println(this.controller.getPlayer().getEnemyList());

        BufferedReader reader = this.getReader();

        String enemySelection = reader.readLine();

        InterEnemy target = selectEnemyToAttack(enemySelection);


        System.out.println("Target enemy: " + target);

        System.out.println("Please, select the character to perform the attack:");
        System.out.println(controller.getPlayer().getMainCharacterArrayList());
        System.out.println("1. Select Marco    2. Select Luis");
        String attackerSelection = reader.readLine();

        this.involvedMainCharacter = attackerSelection.equals("1")? controller.getPlayer().getMarco():controller.getPlayer().getLuis();
        System.out.println("Attacker selected: " + this.involvedMainCharacter);

        System.out.println("Select the attack to perform:");
        System.out.println("1. Jump-Attack   2.Hammer-Attack");
        String attackSelection = reader.readLine();



        attackSelectedEnemy(attackSelection, target);

        System.out.println("########### End of attack turn ###########");


    }

    /**
     * Lets the current involvedMainCharacter attack an enemy.
     * @param attackSelection Selects the type of attack to perform:
     *                        1 -> Jump Attack
     *                        2 -> Hammer Attack
     *
     * @param attackedEnemy Selects the enemy who receives the performed attack.
     */
    public void attackSelectedEnemy(@NotNull String attackSelection, InterEnemy attackedEnemy){
        switch (attackSelection){
            case "1" -> involvedMainCharacter.jumpAttack(attackedEnemy);
            case "2" -> involvedMainCharacter.hammerAttack(attackedEnemy);
           }
    }

    /**
     * Returns the enemy to attack, given the number of enemy selected from the list.
     * @param enemyNumber The string that depicts the enemy number selected from the enemyList.
     * @return The Enemy associated to the entered EnemyNumber
     */
    public InterEnemy selectEnemyToAttack(String enemyNumber){
        return this.controller.getPlayer().getEnemyList().retrieveEnemy(Integer.parseInt(enemyNumber)-1);
    }

    /**
     * Gets the current turn's "Involved Character"
     *
     * The involved character is the mainCharacter of the player which is being currently
     * acted upon (either by using an item on them, or letting them attack an enemy).
     *
     * Note that in the "Passing" turn, there is no action being performed, and thus,
     * the Involved Character should return null.
     *
     * @return The current Involved Character.
     */
    @Override
    public InterMainCharacter getInvolvedMainCharacter() {
        return involvedMainCharacter;
    }

    /**
     * Returns the type of turn played.
     * @return Type of turn played.
     */
    @Override
    public TurnType getType() {
        return this.type;
    }

}

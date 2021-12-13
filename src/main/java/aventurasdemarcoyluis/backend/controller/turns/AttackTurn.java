package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.EnemyList;
import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.AttackType;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;
import org.jetbrains.annotations.NotNull;


public class AttackTurn extends AbstractTurn implements InterAttackTurn {

    private final GameController controller;
    private final TurnType type = TurnType.ATTACK;

    private InterMainCharacter involvedMainCharacter;

    private int enemyNumberToAttack;
    private AttackType attackType;

    /**
     * AttackTurn Constructor
     * By default, sets the class reader as a System.in BufferStream.
     *
     * @param controller the game controller controlling the turn.
     */
    public AttackTurn(GameController controller) {
        super(controller);
        this.controller = controller;
        this.involvedMainCharacter = null;
    }

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     **/
    @Override
    public void main() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {


        InterEnemy target = retrieveEnemyToAttack(this.enemyNumberToAttack);
        System.out.println("Target enemy: " + target);


        System.out.println("The attacking character is: "+ controller.getCurrentTurnOwner());

        switch (controller.getCurrentTurnOwner()){
            case LUIS -> {
                this.involvedMainCharacter = this.controller.getPlayer().getLuis();
            }
            case MARCO -> {
                this.involvedMainCharacter = this.controller.getPlayer().getMarco();
            }
        }

        System.out.println();
        System.out.println("Attacker selected: " + this.involvedMainCharacter);
        System.out.println("Attack Selected: " + this.attackType);
        System.out.println("Target enemy: " + target);

        attackSelectedEnemy(this.attackType, target);

        System.out.println("########### End of attack turn ###########");

    }

    @Override
    public EnemyList getEnemyList(){
        return this.controller.getEnemyList();
    }

    @Override
    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    /**
     * The fist enemy has index 1.
     * @param enemyNumberToAttack The natural number of the enemy to attack
     */
    @Override
    public void setEnemyNumberToAttack(int enemyNumberToAttack){
        this.enemyNumberToAttack = enemyNumberToAttack;
    }

    /**
     * Lets the current involvedMainCharacter attack an enemy.
     *
     * @param attackSelection Selects the type of attack to perform:
     *                        1 -> Jump Attack
     *                        2 -> Hammer Attack
     * @param attackedEnemy   Selects the enemy who receives the performed attack.
     */
    public void attackSelectedEnemy(@NotNull AttackType attackSelection, InterEnemy attackedEnemy) throws InvalidAttackException {
        controller.tryToMakeCharacterAttack(attackSelection, this.involvedMainCharacter, attackedEnemy);
    }

    /**
     * Returns the enemy to attack, given the number of enemy selected from the list.
     *
     * @param enemyNumber The string that depicts the enemy number selected from the enemyList.
     * @return The Enemy associated to the entered EnemyNumber
     */
    @Override
    public InterEnemy retrieveEnemyToAttack(int enemyNumber) throws InvalidSelectionException {
        try{
            return this.controller.getEnemyList().retrieveEnemy(enemyNumber - 1);
        }catch (IndexOutOfBoundsException e){
            throw new InvalidSelectionException("No ha seleccionado un número de enemigo a atacar, o ha seleccionado un número inválido (que no se encuentra en la lista de enemigos).");
        }
    }

    /**
     * Gets the current turn's "Involved Character"
     * <p>
     * The involved character is the mainCharacter of the player which is being currently
     * acted upon (either by using an item on them, or letting them attack an enemy).
     * <p>
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
     *
     * @return Type of turn played.
     */
    @Override
    public TurnType getType() {
        return this.type;
    }

}

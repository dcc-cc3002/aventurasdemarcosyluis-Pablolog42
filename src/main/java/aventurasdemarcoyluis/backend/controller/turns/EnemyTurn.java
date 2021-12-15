package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.enemies.InterEnemy;
import aventurasdemarcoyluis.backend.model.enemies.InterGoombaSpiny;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

/**
 * representation of the turn of the enemy in the game.
 */
public class EnemyTurn extends AbstractTurn implements InterTurn {

    private final GameController controller;
    private InterMainCharacter involvedMainCharacter;
    private final TurnType type = TurnType.ENEMY;

    /**
     * EnemyTurn Constructor
     *
     * @param controller the game controller managing the turn.
     */
    public EnemyTurn(GameController controller) {
        super(controller);
        this.controller = controller;
    }

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.*
     **/
    @Override
    public void main() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {
        // A random character and a random enemy are selected.
        this.involvedMainCharacter = selectCharacter();
        InterEnemy attackingEnemy = this.controller.getEnemyList().retrieveRandomEnemy();

        // Fight! https://pbs.twimg.com/media/DTMfiQOU0AEWIYA.jpg

        System.out.println(attackingEnemy + " ataca aleatoriamente a " + involvedMainCharacter.getType());

        switch (involvedMainCharacter.getType()){
            case MARCO -> {
                try {
                    InterGoombaSpiny enemy = (InterGoombaSpiny) attackingEnemy;
                    enemy.attack(involvedMainCharacter);
                } // In case the casting fails
                catch (Exception e){ throw new InvalidAttackException("Boo can't attack Marco"); }
            }
            case LUIS -> {
                attackingEnemy.attack(this.involvedMainCharacter);
            }
        }
    }


    /**
     * Randomly selects the main character to attack form the player. In case one character is KO,
     * selects the other character.
     *
     * @return the character to be attacked y an enemy.
     **/
    public InterMainCharacter selectCharacter() throws InvalidSelectionException {

        if (this.controller.getPlayer().isPlayerKO()) {
            throw new InvalidSelectionException("The player is KO and can't be attacked!");
        }

        // Randomly elects which player's character to attack
        double rand = Math.random();
        InterMainCharacter attackedCharacter = rand < 0.5 ? this.controller.getPlayer().getLuis() : this.controller.getPlayer().getMarco();

        // The player can't be KO
        if (attackedCharacter.isKO()) {
            attackedCharacter = selectCharacter();
        }

        return attackedCharacter;
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

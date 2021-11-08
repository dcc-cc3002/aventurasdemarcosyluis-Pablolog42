package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.enemies.InterEnemy;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class AttackTurn extends AbstractTurn implements InterTurn {

    private GameController controller;
    private final TurnType type = TurnType.ATTACK;


    private InterMainCharacter involvedMainCharacter;
    private BufferedReader reader;

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

        System.out.println(this.controller);


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



        attackSelectedEnemy(attackSelection, this.involvedMainCharacter, target);

        System.out.println("########### End of attack turn ###########");


    }

    public void attackSelectedEnemy(String attackSelection, InterMainCharacter attackingCharacter, InterEnemy attackedEnemy){
        switch (attackSelection){
            case "1" -> involvedMainCharacter.jumpAttack(attackedEnemy);
            case "2" -> involvedMainCharacter.hammerAttack(attackedEnemy);
            default -> {
                System.out.println("Please, select a valid option.");
            }
        }
    }

    @Override
    public InterMainCharacter getInvolvedMainCharacter() {
        return involvedMainCharacter;
    }


    @Override
    public ArrayList<InterMainCharacter> getCurrentTurnMainCharaters() {
        ArrayList<InterMainCharacter> currentTurnMainCharacters = new ArrayList<>();

        // Agrego solo los personajes principales que no están KO.
        // este metodo es el que se encarga de cumplir con el requisito
        // "Quitar a un personaje del "Turno" cuando esté KO"
        for (InterMainCharacter character : this.controller.getPlayer().getMainCharacterArrayList()){
            if(!character.isKO()) currentTurnMainCharacters.add(character);
        }
        return currentTurnMainCharacters;

    }

    @Override
    public TurnType getType() {
        return this.type;
    }


    public InterEnemy selectEnemyToAttack(String enemyNumber){
        return this.controller.getPlayer().getEnemyList().retrieveEnemy(Integer.parseInt(enemyNumber)-1);
    }



}

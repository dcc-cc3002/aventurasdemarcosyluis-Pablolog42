package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;
import java.util.Scanner;

public class AttackTurn extends AbstractTurn implements InterTurn {

    private Player player;
    private final TurnType type = TurnType.ATTACK;


    private InterMainCharacter attackingMainCharacter;

    public AttackTurn(Player player) {
        super(player);
        this.attackingMainCharacter = null;
//        this.type = TurnType.ATTACK;
    }

    Scanner entrada = new Scanner(System.in);


    public void setAttackingMainCharacter(InterMainCharacter attackingMainCharacter) {
        this.attackingMainCharacter = attackingMainCharacter;
    }

    public InterMainCharacter getAttackingMainCharacter() {
        return attackingMainCharacter;
    }


    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     **/
    @Override
    public void main() {

        System.out.println("####### You are now attacking! #######");

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

    @Override
    public TurnType getType() {
        return this.type;
    }

    public void selectEnemyToAttack(){

    }

}

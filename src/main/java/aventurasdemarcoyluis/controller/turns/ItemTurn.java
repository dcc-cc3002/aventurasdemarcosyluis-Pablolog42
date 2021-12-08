package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.model.items.ItemVault;
import aventurasdemarcoyluis.model.Player;
import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Representation of the turn in which the player uses an item.
 */
public class ItemTurn extends AbstractTurn implements InterItemTurn {

    private final GameController controller;
    private final TurnType type = TurnType.ITEM;

    private BufferedReader reader;

    private ItemType selectedItem = null;



    /**
     * ItemTurn class Constructor
     *
     * @param controller the game controller managing the turn.
     */
    public ItemTurn(GameController controller) {
        super(controller);
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Main method of the current turn.
     * Implement's the logic chain of events according to the turn type.
     *
     * @throws IOException Excepción en caso de error en input de texto.
     **/
    @Override
    public void main() throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {

        Player player = controller.getPlayer();
        TurnOwner turnOwner = controller.getCurrentTurnOwner();

        switch (turnOwner){
            case LUIS -> {involvedMainCharacter = this.controller.getPlayerMainCharacter(EntityType.LUIS);}
            case MARCO -> {involvedMainCharacter = this.controller.getPlayerMainCharacter(EntityType.MARCO);}
        }

        System.out.println("Character to use the item: " + involvedMainCharacter);

        System.out.println(player.getPlayerVault().toString());

        // Case character KO
        assert involvedMainCharacter != null;
        if (involvedMainCharacter.isKO()) {
            throw new InvalidSelectionException("The chosen character is K.O. and can't use an item. Please select another character!");
        }


//        // Case Player doesn't have enough item
//        if (player.getPlayerVault().getItemAmount(Objects.requireNonNull(selectedItem)) == 0) {
//            System.out.println("You don't have enough " + selectedItem + "!. Please select another item to use!");
//            main();
//            return;
//        }

        System.out.println(involvedMainCharacter.getType() + " has successfully used a " + selectedItem);

        // After all checks, the item is used.

        player.tryToUseItem(selectedItem, involvedMainCharacter);

        System.out.println(involvedMainCharacter.getType() + "'s new stats are:");
        System.out.println(involvedMainCharacter + "\n");



    }

    public void chooseItemToUse(ItemType selectedItem) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException {
        // Case Player doesn't have enough item
        if (this.controller.getPlayer().getPlayerVault().getItemAmount(Objects.requireNonNull(selectedItem)) == 0) {
            throw new InvalidSelectionException("The player doesn't have any " + selectedItem + " to use!");
        }

        this.selectedItem = selectedItem;
        main();


    }

    public ItemVault getPlayerVault(){
        return this.controller.getPlayer().getPlayerVault();
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
     * Sets a Buffered Reader stream as an input to a turn.
     * The BufferedReader can be instanced with a string
     * or with the use of the System.in input.
     *
     * @param reader The reader to set.
     */
    @Override
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}



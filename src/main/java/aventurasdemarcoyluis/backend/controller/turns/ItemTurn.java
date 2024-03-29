package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.GameController;
import aventurasdemarcoyluis.backend.model.items.ItemVault;
import aventurasdemarcoyluis.backend.model.Player;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.items.ItemType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Representation of the turn in which the player uses an item.
 */
public class ItemTurn extends AbstractTurn implements InterTurn {

    private final GameController controller;
    private final TurnType type = TurnType.ITEM;

    private ItemType selectedItem = null;



    /**
     * ItemTurn class Constructor
     *
     * @param controller the game controller managing the turn.
     */
    public ItemTurn(GameController controller) {
        super(controller);
        this.controller = controller;
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
     * Set's a selected item to be used to the turn.
     * @param selectedItem the item to be set.
     */
    public void setSelectedItem(ItemType selectedItem) {
        this.selectedItem = selectedItem;
    }

}
package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.util.Scanner;

public class ItemTurn implements InterTurn{

    private Player player;
    private TurnType type;

    public ItemTurn(Player player) {
        this.player = player;
        this.type = TurnType.ITEM;
    }

    Scanner entrada = new Scanner(System.in);

    @Override
    public void main() {

        System.out.println(player.getPlayerVault().toString());

        System.out.println("Please choose a character to use the item: ");
        System.out.println("1. " + player.getMarco()+"\n" + "2. " + player.getLuis());
        String character = entrada.nextLine();

        InterMainCharacter selectedCharacter = null;

        switch (character){
            case "1" -> selectedCharacter = player.getMarco();
            case "2" -> selectedCharacter = player.getLuis();
            default -> {
                // Case invalid selection
                System.out.println("Please enter a valid selection! \n");
                main(); return;
            }
        }


        // Case character KO
        if(selectedCharacter.isKO()){
            System.out.println("The chosen character is K.O. and can't use an item. Please select another character! \n");
            main(); return;
        }

        System.out.println("Please choose an item to use: ");
        System.out.println("1. Use Honey Syrup       2. Use Red Mushroom");
        String item = entrada.nextLine();

        ItemType selectedItem = null;

        switch (item){
            case "1" -> selectedItem = ItemType.HONEYSYRUP;
            case "2" -> selectedItem = ItemType.REDMUSHROOM;
            default -> {
                // Case Invalid Selection
                System.out.println("Please enter a valid selection! \n");
                main(); return;
            }
        }


        // Case Player doesn't have enough item
        if(player.getPlayerVault().getItemAmount(selectedItem) == 0){
            System.out.println("You don't have enough " + selectedItem + "!. Please select another item to use!");
            main(); return;
        }

        System.out.println(selectedCharacter.getType() + " has successfully used a " + selectedItem + "\n");

        // After all checks, the item is used.
        player.useItem(selectedItem, selectedCharacter);

    }

    @Override
    public TurnType getType() {
        return type;
    }
}

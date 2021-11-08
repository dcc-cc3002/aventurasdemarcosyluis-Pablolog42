package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.GameController;
import aventurasdemarcoyluis.controller.Player;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ItemTurn extends AbstractTurn implements InterTurn{

    private GameController controller;
    private final TurnType type = TurnType.ITEM;
    private InterMainCharacter involvedMainCharacter=null;
    private BufferedReader reader;

    public ItemTurn(GameController controller) {
        super(controller);
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

 
    @Override
    public void main() throws IOException {

        Player player = controller.getPlayer();

        System.out.println(player.getPlayerVault().toString());

        System.out.println("Please choose a character to use the item: ");
        System.out.println("1. " + player.getMarco()+"\n" + "2. " + player.getLuis());
        String selectedCharacter = reader.readLine();
        
        switch (selectedCharacter){
            case "1" -> this.involvedMainCharacter = player.getMarco();
            case "2" -> this.involvedMainCharacter = player.getLuis();
            default -> {
                this.involvedMainCharacter = null;
            }
        }


        // Case character KO
        if(involvedMainCharacter.isKO()){
            System.out.println("The chosen character is K.O. and can't use an item. Please select another character! \n");
        }


        System.out.println("Please choose an item to use: ");
        System.out.println("1. Use Honey Syrup       2. Use Red Mushroom");
        String item = reader.readLine();

        ItemType selectedItem;

        switch (item){
            case "1" -> selectedItem = ItemType.HONEYSYRUP;
            case "2" -> selectedItem = ItemType.REDMUSHROOM;
            default -> {
                selectedItem = null;
            }
        }


        // Case Player doesn't have enough item
        if(player.getPlayerVault().getItemAmount(selectedItem) == 0){
            System.out.println("You don't have enough " + selectedItem + "!. Please select another item to use!");
            main(); return;
        }

        System.out.println(involvedMainCharacter.getType() + " has successfully used a " + selectedItem);

        // After all checks, the item is used.
        player.useItem(selectedItem, involvedMainCharacter);

        System.out.println(involvedMainCharacter.getType() + "'s new stats are:" );
        System.out.println(involvedMainCharacter + "\n");

    }



    @Override
    public TurnType getType() {
        return this.type;
    }

    @Override
    public InterMainCharacter getInvolvedMainCharacter() {
        return involvedMainCharacter;
    }

    @Override
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}

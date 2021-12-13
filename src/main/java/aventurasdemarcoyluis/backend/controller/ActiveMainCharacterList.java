package aventurasdemarcoyluis.backend.controller;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;

public class ActiveMainCharacterList {

    private final ArrayList<InterMainCharacter> activeMainCharacterList;

    private final GameController controller;

    public ActiveMainCharacterList(GameController controller){
        this.activeMainCharacterList = new ArrayList<>();
        this.controller = controller;
    }

    /**
     * Adds the player's mainCharacters to the active main character list.
     */
    public void initialSetup(){
        activeMainCharacterList.add(0,controller.getPlayer().getMarco());
        activeMainCharacterList.add(1,controller.getPlayer().getLuis());
    }

    public void removeMainCharacterFromActiveList(EntityType mainCharacterToRemove){
        this.activeMainCharacterList.removeIf(character -> character.getType() == mainCharacterToRemove);
    }

    public InterMainCharacter retrieveMainCharacter(EntityType mainCharacterToRetrieve) throws InvalidSelectionException {
        for (InterMainCharacter character : this.activeMainCharacterList){
            if(character.getType() == mainCharacterToRetrieve){
                return character;
            }
        }
        // If the character isn't in the list
        throw new InvalidSelectionException("The character you want to retrieve from the list is K.O. or Invalid.");
    }


    /**
     * Clears all characters from the list, and sets it up again, with the indicated characters.
     * @param newMarco Marco to be restored
     * @param newLuis Luis to be restored
     */
    public void restoreList(InterMainCharacter newMarco, InterMainCharacter newLuis){
        activeMainCharacterList.clear();
        activeMainCharacterList.add(0,newMarco);
        activeMainCharacterList.add(1,newLuis);
    }


    public ArrayList<InterMainCharacter> getActiveMainCharacterList() {
        return activeMainCharacterList;
    }

}

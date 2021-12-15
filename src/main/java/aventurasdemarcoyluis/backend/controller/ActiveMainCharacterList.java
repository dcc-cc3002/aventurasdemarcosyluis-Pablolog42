package aventurasdemarcoyluis.backend.controller;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.model.EntityType;
import aventurasdemarcoyluis.backend.model.maincharacters.InterMainCharacter;

import java.util.ArrayList;

/**
 * Class denoting a list of main characters that are not KO.
 */
public class ActiveMainCharacterList {

    private final ArrayList<InterMainCharacter> activeMainCharacterList;

    private final GameController controller;

    /**
     * ActiveMainCharacterList Constructor.
     * @param controller the controller handling the game.
     */
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

    /**
     * Removes a specific Main Character from the list.
     * @param mainCharacterToRemove the character to remove.
     */
    public void removeMainCharacterFromActiveList(EntityType mainCharacterToRemove){
        this.activeMainCharacterList.removeIf(character -> character.getType() == mainCharacterToRemove);
    }

    /**
     * Retrieves a specific main character from the active list.
     * @param mainCharacterToRetrieve the Type of the character to retrieve (marco or luis)
     * @return The InterMainCharacter retrieved from the list.
     * @throws InvalidSelectionException In case there are no active characters (i.e. all characters are KO )
     */
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

    /**
     * Gets the active main character list.
     * @return the active main character list
     */
    public ArrayList<InterMainCharacter> getActiveMainCharacterList() {
        return activeMainCharacterList;
    }

}

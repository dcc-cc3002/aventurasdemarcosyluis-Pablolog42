package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.phases.FinishTurnPhase;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.turns.InterItemTurn;
import aventurasdemarcoyluis.model.EntityType;
import aventurasdemarcoyluis.model.items.ItemType;
import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

public class UseItemPhase extends Phase {

    InterItemTurn currentItemTurn;

    public UseItemPhase(InterItemTurn currentItemTurn){
        this.currentItemTurn = currentItemTurn;
        this.canTransitionPhase = false;
        this.canStartNewTurn = false;
    }


    public void useItem(EntityType mainCharacterToUseItem, ItemType itemToBeUsed){

        InterMainCharacter selectedCharacter = controller.getPlayerMainCharacter(mainCharacterToUseItem);

        try {
            controller.getPlayer().tryToUseItem(itemToBeUsed, selectedCharacter);
        }catch (InvalidSelectionException e){
            e.printStackTrace();
            return;
        }
        this.canTransitionPhase=true;

    }


    public void toFinishTurnPhase(){
        controller.tryToChangePhase(new FinishTurnPhase());
    }


    @Override
    public String toString() {
        return "UseItemPhase";
    }


}

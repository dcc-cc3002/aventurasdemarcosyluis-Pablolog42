package aventurasdemarcoyluis.controller.phases.characterPhases;

import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.phases.Phase;
import aventurasdemarcoyluis.controller.turns.InterItemTurn;
import aventurasdemarcoyluis.model.items.ItemType;

public class WaitSelectItemPhase extends Phase {


    InterItemTurn currentItemTurn = (InterItemTurn) controller.getCurrentTurn();


    public WaitSelectItemPhase(){
        super();
        canTransitionPhase=false;
        canStartNewTurn = false;
    }


    public void toUseItemPhase(){
        controller.tryToChangePhase(new UseItemPhase(currentItemTurn));
    }



    public void chooseItemToUse(ItemType itemType){
        try{
            currentItemTurn.chooseItemToUse(itemType);
        }catch (InvalidSelectionException e){
            e.printStackTrace();
            return;
        }catch (Exception ignored){
            return;
        }
        // Once an item has been selected, go to use phase.
        canTransitionPhase=true;
    }




    @Override
    public String toString() {
        return "WaitSelectItemPhase";
    }

}

package aventurasdemarcoyluis.controller.turns;

import aventurasdemarcoyluis.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.model.items.ItemType;

public interface InterItemTurn extends InterTurn{
    void chooseItemToUse(ItemType itemType) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException;
}

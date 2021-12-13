package aventurasdemarcoyluis.backend.controller.turns;

import aventurasdemarcoyluis.backend.controller.exeptions.InvalidAttackException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidSelectionException;
import aventurasdemarcoyluis.backend.controller.exeptions.InvalidTransitionException;
import aventurasdemarcoyluis.backend.model.items.ItemType;

public interface InterItemTurn extends InterTurn{
    void chooseItemToUse(ItemType itemType) throws InvalidSelectionException, InvalidAttackException, InvalidTransitionException;
}

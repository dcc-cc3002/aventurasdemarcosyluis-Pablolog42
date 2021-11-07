package aventurasdemarcoyluis.model.items;

import aventurasdemarcoyluis.model.maincharacters.InterMainCharacter;

//TODO: Comentar la interface
public interface InterItem {

    void useItem(InterMainCharacter character);

    ItemType getType();
}

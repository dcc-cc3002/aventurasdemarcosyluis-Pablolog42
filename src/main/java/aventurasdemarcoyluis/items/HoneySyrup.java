package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.players.AbstractPlayer;

public class HoneySyrup extends AbstractItem {
    public HoneySyrup() {
        super(ItemType.HONEYSYRUP, "Honey Syrup");
    }

    @Override
    public void useItem(AbstractPlayer player){
        player.restoreFP(3);
    }
}
package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.players.AbstractPlayer;

/*
    HoneySyrup item Class
 */
public class HoneySyrup extends AbstractItem {
    public HoneySyrup() {
        super(ItemType.HONEYSYRUP, "Honey Syrup");
    }

    /**
     * Uses the HoneySyrup item.
     * Restores 3 FP to the player
     *
     * @param player The player to use the item.
     **/
    @Override
    public void useItem(AbstractPlayer player){
        player.restoreFP(3);
    }
}
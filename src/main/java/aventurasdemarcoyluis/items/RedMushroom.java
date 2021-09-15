package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.players.AbstractPlayer;

public class RedMushroom extends AbstractItem {
    public RedMushroom() {
        super(ItemType.REDMUSHROOM, "Red Mushroom");
    }

    @Override
    public void useItem(AbstractPlayer player) {
        player.restoreHP(0.1 * player.getMaxHP());
    }
}

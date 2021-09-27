package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.players.AbstractPlayer;

/*
    RedMushroom item Class
 */
public class RedMushroom extends AbstractItem {
    public RedMushroom() {
        super(ItemType.REDMUSHROOM, "Red Mushroom");
    }

    /**
     * Uses the Red Mooshroom item.
     * Restores 10% of the player's maxHP to the player's current health.
     *
     * @param player The player to use the item.
     **/
    @Override
    public void useItem(AbstractPlayer player) {
        player.restoreHP(0.1 * player.getMaxHP());
    }
}

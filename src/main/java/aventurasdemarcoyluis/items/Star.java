package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.players.AbstractPlayer;

/*
    Star item Class
 */
public class Star extends AbstractItem {
    public Star() {
        super(ItemType.STAR, "Star");
    }

    /**
     * Uses the Star item.
     * Activates the player's "Invincible Mode"
     *
     * @param player The player to use the item.
     **/
    @Override
    public void useItem(AbstractPlayer player) {
        player.setInvincible(true);
    }
}

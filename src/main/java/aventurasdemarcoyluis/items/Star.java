package aventurasdemarcoyluis.items;

import aventurasdemarcoyluis.players.AbstractPlayer;

public class Star extends AbstractItem {
    public Star() {
        super(ItemType.STAR, "Star");
    }

    @Override
    public void useItem(AbstractPlayer player) {
        player.setInvincible(true);
    }
}

package aventurasdemarcoyluis;

class Star extends AbstractItem {
    public Star(ItemType ITEMTYPE, String ITEMNAME) {
        super(ItemType.STAR, "Star");
    }

    @Override
    public void useItem(AbstractPlayer player){
        player.isInvincible = true;
    }
}

class RedMushroom extends AbstractItem {
    public RedMushroom(ItemType ITEMTYPE, String ITEMNAME) {
        super(ItemType.REDMUSHROOM, "Red Mushroom");
    }

    @Override
    public void useItem(AbstractPlayer player){
        player.restoreHP(0.1* player.getHp());
    }
}

class HoneySyrup extends AbstractItem {
    public HoneySyrup(ItemType ITEMTYPE, String ITEMNAME) {
        super(ItemType.REDMUSHROOM, "Red Mushroom");
    }

    @Override
    public void useItem(AbstractPlayer player){
        player.restoreFP(3);
    }
}
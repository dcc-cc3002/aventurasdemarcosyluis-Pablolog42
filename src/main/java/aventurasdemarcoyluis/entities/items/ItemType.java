package aventurasdemarcoyluis.entities.items;

public enum ItemType {
    REDMUSHROOM {
        @Override
        public String toString() {
            return "Red Mushroom";
        }
    }
    ,HONEYSYRUP {
        @Override
        public String toString() {
            return "Honey Syrup";
        }
    }
}

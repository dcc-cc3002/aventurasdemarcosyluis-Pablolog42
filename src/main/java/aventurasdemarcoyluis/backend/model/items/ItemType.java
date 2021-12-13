package aventurasdemarcoyluis.backend.model.items;

/**
 * Enum denoting the two types of item in the game.
 * An item can be:
 * 1. Honey Syrup
 * 2. Red Mushroom.
 */
public enum ItemType {
    REDMUSHROOM {
        /**
         * Provides a string representation of an ItemType.
         * @return a string representation of an ItemType
         */
        @Override
        public String toString() {
            return "Red Mushroom";
        }
    }, HONEYSYRUP {
        /**
         * Provides a string representation of an ItemType.
         * @return a string representation of an ItemType
         */
        @Override
        public String toString() {
            return "Honey Syrup";
        }
    }
}

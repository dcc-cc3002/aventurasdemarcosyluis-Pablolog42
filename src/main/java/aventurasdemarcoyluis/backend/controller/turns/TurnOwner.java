package aventurasdemarcoyluis.backend.controller.turns;

/**
 * Enum denoting the possible "owners" of a turn.
 * A turn can either be played by a Player or an Enemy.
 */
public enum TurnOwner {

    MARCO {
        /**
         * Provides a string representation of an EntityType.
         * @return a string representation of an EntityType
         */
        @Override
        public String toString() {
            return "Marco";
        }



    }, LUIS {
        /**
         * Provides a string representation of an EntityType.
         * @return a string representation of an EntityType
         */
        @Override
        public String toString() {
            return "Luis";
        }



    }, ENEMY {
        /**
         * Provides a string representation of an EntityType.
         * @return a string representation of an EntityType
         */
        @Override
        public String toString() {
            return "Enemy";
        }

    };


    private static final TurnOwner[] values = values();

    /**
     * Method that returns a turn owner given an integer, in correspondence to the order of the place they appear in the enum
     * @param ordinal the place of the TurnOwner to return
     * @return The turn owner associated to the given ordinal number.
     */
    public static TurnOwner getTurnOwnerFromId(int ordinal) {
        return values[ordinal];
    }


}

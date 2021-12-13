package aventurasdemarcoyluis.backend.model;

/**
 * Enum denoting the five types of entity in the game.
 * An entity can be:
 * 1. Goomba
 * 2. Spiny
 * 3. Boo
 * 4. Marco
 * 5. Luis
 */
public enum EntityType {

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



    }, GOOMBA {
        /**
         * Provides a string representation of an EntityType.
         * @return a string representation of an EntityType
         */
        @Override
        public String toString() {
            return "Goomba";
        }

    }, BOO {
        /**
         * Provides a string representation of an EntityType.
         * @return a string representation of an EntityType
         */
        @Override
        public String toString() {
            return "Boo";
        }




    }, SPINY {
        /**
         * Provides a string representation of an EntityType.
         * @return a string representation of an EntityType
         */
        @Override
        public String toString() {
            return "Spiny";
        }

    }
}

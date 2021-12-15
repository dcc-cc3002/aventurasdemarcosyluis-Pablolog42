package aventurasdemarcoyluis.backend.model.enemies;

import java.util.Random;

/**
 * Enum denoting the three types of enemy in the game.
 * An enemy can be:
 * 1. Goomba
 * 2. Spiny
 * 3. Boo
 */
public enum EnemyType {

    GOOMBA {
        /**
         * Provides a string representation of an EnemyType.
         * @return a string representation of an EnemyType
         */
        @Override
        public String toString() {
            return "Goomba";
        }
    }, BOO {
        /**
         * Provides a string representation of an EnemyType.
         * @return a string representation of an EnemyType
         */
        @Override
        public String toString() {
            return "Boo";
        }
    }, SPINY {
        /**
         * Provides a string representation of an EnemyType.
         * @return a string representation of an EnemyType
         */
        @Override
        public String toString() {
            return "Spiny";
        }
    };

    /**
     * Provides a random enemyType from this enum.
     *
     * @return a randomly selected enemyType from the enum.
     */
    public static EnemyType getRandomEnemyType() {
        return EnemyType.values()[new Random().nextInt(EnemyType.values().length)];
    }
}


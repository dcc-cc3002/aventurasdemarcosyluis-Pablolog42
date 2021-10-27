package aventurasdemarcoyluis.entities.enemies;

import java.util.Random;

public enum EnemyType {
    //TODO: Documentar enum
    GOOMBA{
        @Override
        public String toString() {
            return "Goomba";
        }
    },BOO{
        @Override
        public String toString() {
            return "Boo";
        }
    },SPINY{
        @Override
        public String toString() {
            return "Spiny";
        }
    };

    // TODO Comentar este método
    public static EnemyType getRandomEnemyType(){
        return EnemyType.values()[new Random().nextInt(EnemyType.values().length)];
    }
}


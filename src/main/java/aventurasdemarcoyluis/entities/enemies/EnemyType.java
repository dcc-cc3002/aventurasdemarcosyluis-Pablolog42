package aventurasdemarcoyluis.entities.enemies;

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
    }
}

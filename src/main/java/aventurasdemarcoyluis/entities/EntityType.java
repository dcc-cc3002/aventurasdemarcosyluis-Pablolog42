package aventurasdemarcoyluis.entities;

public enum EntityType {
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
    },MARCO{
        @Override
        public String toString() {
            return "Marco";
        }
    },LUIS{
        @Override
        public String toString() {
            return "Luis";
        }
    }
}

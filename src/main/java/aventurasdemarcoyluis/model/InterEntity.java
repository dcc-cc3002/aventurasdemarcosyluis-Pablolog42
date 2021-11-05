package aventurasdemarcoyluis.model;

//TODO: Comentar la interface
public interface InterEntity {
    void receiveDamage(double damage);
    void restoreHP(double hpToRestore);
    double getAtk();
    int getLvl();
    double getMaxHP();
    String getName();
    String toString();

    double computeDmg(double k, InterEntity player);

    boolean isKO();

    EntityType getType();
}

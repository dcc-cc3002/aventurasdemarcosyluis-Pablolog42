package aventurasdemarcoyluis.entities;

//TODO: Comentar la interface
public interface InterEntity {
    void receiveDamage(double damage);
    void restoreHP(double hpToRestore);
    void restoreFP(int fpToRestore);
    double getAtk();
    int getLvl();
    double getMaxHP();
    String getName();
}

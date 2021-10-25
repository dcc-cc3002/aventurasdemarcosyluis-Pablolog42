package aventurasdemarcoyluis.entities;

public interface InterEntity {
    void receiveDamage(double damage);
    double computeDmg(double k, InterEntity attacker);
    void restoreHP(double hpToRestore);
    void restoreFP(int fpToRestore);
    double getAtk();
    int getLvl();

    double getMaxHP();
}

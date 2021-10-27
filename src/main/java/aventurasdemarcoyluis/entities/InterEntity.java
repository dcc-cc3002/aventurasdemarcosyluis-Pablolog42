package aventurasdemarcoyluis.entities;

import aventurasdemarcoyluis.entities.maincharacters.InterMainCharacter;

//TODO: Comentar la interface
public interface InterEntity {
    void receiveDamage(double damage);
    void restoreHP(double hpToRestore);
    double getAtk();
    int getLvl();
    double getMaxHP();
    String getName();

    double computeDmg(double k, InterEntity player);
}
package aventurasdemarcoyluis;

public abstract class AbstractEntity {

    private double atk;
    private double def;
    private int fp;
    private double hp;
    private double maxHP;
    private int lvl=1;
    private boolean isKO;
    private EntityType type;
    private String name;


    /**
     * Creates a new AbstractEnemy
     * @param ATK jumpAttack points
     * @param DEF defense points
     * @param FP FP
     * @param HP  heal points
     * @param LVL level of the Unit
     * @param TYPE type of the enemy (see enum "EnemyType")
     * @param NAME name of the enemy to deploy (for example, "Mr. Claudio Goomba")
     *
     */
    public AbstractEntity(double ATK, double DEF, int FP, double HP, double MAXHP, int LVL, EntityType TYPE, String NAME){
        this.atk=ATK;
        def=DEF;
        hp=HP;
        maxHP=MAXHP;
        lvl=LVL;
        type=TYPE;
        name = NAME;
        fp = FP;
        // By default, every enemy is not KO
        isKO = false;
    }

    public void receiveDamage(double damage) {
        double targetHp = this.hp - damage;
        if(this.isKO){
            System.out.println(this.name + " can't receive damage, they're K.O.!");
            return;
        }
        if(targetHp <= 0){
            System.out.println(this.name + " has no HP left, they're now K.O!");
            // a dead enemy has 0 HP
            this.hp = 0;
            this.isKO = true;
            return;
        }
        System.out.println(this.name + " has received " + damage + " damage points!");
        this.hp = targetHp;
    }

    public void restoreHP(double hpToRestore){
        double targetHP = this.hp + hpToRestore;

        if(this.maxHP<targetHP){
            this.setHp(maxHP);
            if(0<this.hp){this.setKO(false);}
            return;
        }

        this.setHp(targetHP);

        // Implementation Note: the only way to increase the hp of a character should be using this method.
        // This is because this method allows to change the "isKO" property (lets an incapacitated player play again if they heal)
        if(0<this.hp){this.setKO(false);}

    };

    public double computeDmg(double k, AbstractEntity attacker){
        return (k * attacker.atk * attacker.lvl) / this.getDef();
    }




    // Setters and getters


    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public int getFp() {
        return fp;
    }

    public void setFp(int fp) {
        this.fp = fp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public boolean isKO() {
        return isKO;
    }

    public void setKO(boolean KO) {
        isKO = KO;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

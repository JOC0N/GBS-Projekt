package de.gbs.projekt.stats;

public enum EntityStats {
    HEALTH(1,"Health",0,100,1000),
    MANA(2,"Mana",-1000,0,1000),
    EXPERIENCE(3,"Experience",0,0,1000000),

    DAMAGE(10,"Damage",-1000,0,1000),
    DEFENSE(11,"Defense",-1000,0,1000),
    REGENERATION(12,"Regeneration",-1000,0,1000),

    SPEED(20,"Speed",0,5,1000),
    SPRINT(21,"Sprint",0,10,1000);

    private final int id;
    private final String name;
    private final int min;
    private final int normal;
    private final int max;

    EntityStats(int id, String name, int min, int normal, int max) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.normal = normal;
        this.max = max;
    }

    public boolean isInBounds(int statValue) {
        return statValue >= min && statValue <= max;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getMin() {
        return min;
    }
    public int getNormal() {
        return normal;
    }
    public int getMax() {
        return max;
    }
}

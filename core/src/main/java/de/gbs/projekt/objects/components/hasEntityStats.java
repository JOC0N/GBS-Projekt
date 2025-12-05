package de.gbs.projekt.objects.components;

import de.gbs.projekt.stats.EntityStats;

import java.util.HashMap;

public interface hasEntityStats {
    HashMap<String,Integer> stats = new HashMap<String, Integer>();

    void setDefaultStats();

    default void changeStat(EntityStats stat, int value) {
        if (stat.isInBounds(stats.get(stat.getName()) + value)){
            stats.put(stat.getName(), stats.get(stat.getName()) + value);
        } else {
            System.out.println("Stat " + stat.getName() + " out of bounds: " + (stats.get(stat.getName()) + value));
        }

    }

    default void setStat(EntityStats stat, int value) {
        if (stat.isInBounds(value)){
            stats.put(stat.getName(), value);
        }
    }

    default int getStat(EntityStats stat) {
        return stats.get(stat.getName());
    }

}

package de.gbs.projekt.items;

public enum Ids {
    COIN("Coin",1, Rarity.COMMON, 32),
    HEALTHPOTION("Health Potion",2, Rarity.UNCOMMON, 8),
    MANAPOTION("Mana Potion",3, Rarity.UNCOMMON, 8),

    ;

    private final int maxStackSize;
    private final Rarity rarity;
    private final int id;
    private final String name;

    Ids(String name, int id, Rarity rarity, int maxStackSize) {
        this.name = name;
        this.id = id;
        this.rarity = rarity;
        this.maxStackSize = maxStackSize;
    }
}

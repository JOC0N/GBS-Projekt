package de.gbs.projekt.items;

  enum Rarity {
    UNATTTAINABLE("Unattainable", "Gray"),
    COMMON("Common", "White"),
    UNCOMMON("Uncommon", "Green"),
    RARE("Rare", "Blue"),
    EPIC("Epic", "Purple"),
    LEGENDARY("Legendary", "Gold"),
    MYTHIC("Mythic", "Red"),
    FORBIDDEN("Forbidden","Black");

    private final String name;
    private final String color;

    Rarity(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}

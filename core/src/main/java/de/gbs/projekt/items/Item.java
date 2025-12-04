package de.gbs.projekt.items;

import com.badlogic.gdx.graphics.Texture;

import java.util.UUID;

public abstract class Item {

    protected String name;
    protected Texture texture;
    protected Rarity rarity;
    protected int maxStackSize;

    public Item(String name, Texture texture, Rarity rarity, int maxStackSize) {
        this.name = name;
        this.texture = texture;
        this.rarity = rarity;
        this.maxStackSize = maxStackSize;
    }
}

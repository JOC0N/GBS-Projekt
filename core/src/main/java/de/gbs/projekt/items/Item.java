package de.gbs.projekt.items;

import com.badlogic.gdx.graphics.Texture;

import java.util.UUID;

public class Item {

    protected String name;
    protected Texture texture;
    protected Rarity rarity;
    protected UUID uuid;

    private Item(String name, Texture texture, Rarity rarity) {
        this.name = name;
        this.texture = texture;
        this.rarity = rarity;
    }
}

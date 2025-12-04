package de.gbs.projekt.items;

import com.badlogic.gdx.graphics.Texture;

public class GenericItem extends Item {

    private int id;

    public GenericItem(String name, Texture texture, Rarity rarity, int maxStackSize) {
        super(name, texture, rarity, maxStackSize);

    }
}

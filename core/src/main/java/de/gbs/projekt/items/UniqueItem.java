package de.gbs.projekt.items;


import com.badlogic.gdx.graphics.Texture;

import java.util.UUID;

public class UniqueItem extends Item {

    private UUID uuid;
    
    public UniqueItem(String name, Texture texture, Rarity rarity) {
        super(name, texture, rarity, 1);
    }
    //random parameter generieren pro item in der welt also at schaden , schnelligkeit, mana etc
}

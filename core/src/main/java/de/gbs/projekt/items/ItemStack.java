package de.gbs.projekt.items;

public class ItemStack {

    private Item item;
    private int stackSize;

    public ItemStack(Item item, int stackSize) {
        this.item = item;
        this.stackSize = stackSize;
    }
}

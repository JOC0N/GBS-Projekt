package de.gbs.projekt.items;

public class ItemStack {

    private Item item;
    private int stackSize;

    public ItemStack(Item item, int stackSize) {
        this.item = item;
        this.stackSize = stackSize;
    }

    public Item getItem() {
        return item;
    }

    public int getStackSize() {
        return stackSize;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public boolean checkStackFull() {
        return stackSize >= item.maxStackSize;
    }


}

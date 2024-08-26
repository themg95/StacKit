package dev.mg95.stackit;

import dev.mg95.colon3lib.config.Category;
import dev.mg95.colon3lib.config.Config;
import dev.mg95.colon3lib.config.Option;

public class StackItConfig extends Config {
    @Category
    public ItemCategory potions = new ItemCategory(true, 16);

    @Category
    public ItemCategory splashPotions = new ItemCategory(true, 16);

    @Category
    public ItemCategory soups = new ItemCategory(true, 16);

    @Category
    public ItemCategory enchantedBooks = new ItemCategory(true, 16);

    @Category
    public ItemCategory buckets = new ItemCategory(true, 16);

    @Category
    public ItemCategory boats = new ItemCategory(true, 64);

    @Category
    public ItemCategory minecarts = new ItemCategory(true, 64);

    @Category
    public ItemCategory beds = new ItemCategory(true, 64);

    @Category
    public ItemCategory discs = new ItemCategory(true, 16);

    @Category
    public ItemCategory goatHorns = new ItemCategory(true, 16);

    @Category
    public ItemCategory patterns = new ItemCategory(true, 16);

    @Category
    public ItemCategory saddles = new ItemCategory(true, 16);

    @Category
    public ItemCategory horseArmor = new ItemCategory(true, 16);

    @Category
    public ItemCategory cakes = new ItemCategory(true, 16);

    @Category
    public ItemCategory totems = new ItemCategory(true, 16);

    public static class ItemCategory extends Config {
        @Option
        public boolean enabled;
        @Option
        public int stackSize;

        public ItemCategory(boolean enabled, int stackSize) {
            this.enabled = enabled;
            this.stackSize = stackSize;
        }
    }

    public StackItConfig() {
        init(this, "stackit");
    }
}

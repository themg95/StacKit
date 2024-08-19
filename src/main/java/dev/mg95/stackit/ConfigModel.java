package dev.mg95.stackit;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Nest;
import net.minecraft.item.Item;

import static net.minecraft.item.Items.*;

@Config(name = "stackit-config", wrapperName = "ModConfig")
public class ConfigModel {
    @Nest
    public ItemCategory potions = new ItemCategory(true, 16);

    @Nest
    public ItemCategory splashPotions = new ItemCategory(true, 16);

    @Nest
    public ItemCategory soups = new ItemCategory(true, 16);

    @Nest
    public ItemCategory enchantedBooks = new ItemCategory(true, 16);

    @Nest
    public ItemCategory buckets = new ItemCategory(true, 16);

    @Nest
    public ItemCategory boats = new ItemCategory(true, 64);

    @Nest
    public ItemCategory minecarts = new ItemCategory(true, 64);

    @Nest
    public ItemCategory beds = new ItemCategory(true, 64);

    @Nest
    public ItemCategory discs = new ItemCategory(true, 16);

    @Nest
    public ItemCategory goatHorns = new ItemCategory(true, 16);

    @Nest
    public ItemCategory patterns = new ItemCategory(true, 16);

    @Nest
    public ItemCategory saddles = new ItemCategory(true, 16);

    @Nest
    public ItemCategory horseArmor = new ItemCategory(true, 16);

    @Nest
    public ItemCategory cakes = new ItemCategory(true, 16);

    @Nest
    public ItemCategory totems = new ItemCategory(true, 16);

    public static class ItemCategory {
        public boolean enabled;
        public int stackSize;

        public ItemCategory(boolean enabled, int stackSize) {
            this.enabled = enabled;
            this.stackSize = stackSize;
        }
    }
}

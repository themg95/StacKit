package dev.mg95.stackit;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;

import java.util.Map;

import static net.minecraft.item.Items.*;

public class StacKit implements ModInitializer {
    public StackItConfig Config = new StackItConfig();
    public static final Map<String, Item[]> itemsMap = Map.ofEntries(
            Map.entry("potions", new Item[]{POTION}),
            Map.entry("splashPotions", new Item[]{SPLASH_POTION}),
            Map.entry("soups", new Item[]{SUSPICIOUS_STEW, MUSHROOM_STEW, RABBIT_STEW, BEETROOT_SOUP}),
            Map.entry("enchantedBooks", new Item[]{ENCHANTED_BOOK}),
            Map.entry("buckets", new Item[]{AXOLOTL_BUCKET, COD_BUCKET, LAVA_BUCKET, MILK_BUCKET, SALMON_BUCKET, PUFFERFISH_BUCKET, WATER_BUCKET, POWDER_SNOW_BUCKET, TADPOLE_BUCKET, TROPICAL_FISH_BUCKET}),
            Map.entry("boats", new Item[]{OAK_BOAT, OAK_CHEST_BOAT, SPRUCE_BOAT, SPRUCE_CHEST_BOAT, BIRCH_BOAT, BIRCH_CHEST_BOAT, JUNGLE_BOAT, JUNGLE_CHEST_BOAT, ACACIA_BOAT, ACACIA_CHEST_BOAT, DARK_OAK_BOAT, DARK_OAK_CHEST_BOAT, MANGROVE_BOAT, MANGROVE_CHEST_BOAT, CHERRY_BOAT, CHERRY_CHEST_BOAT, BAMBOO_RAFT, BAMBOO_CHEST_RAFT}),
            Map.entry("minecarts", new Item[]{MINECART, CHEST_MINECART, FURNACE_MINECART, TNT_MINECART, HOPPER_MINECART, COMMAND_BLOCK_MINECART}),
            Map.entry("beds", new Item[]{WHITE_BED, ORANGE_BED, MAGENTA_BED, LIGHT_BLUE_BED, YELLOW_BED, LIME_BED, PINK_BED, GRAY_BED, LIGHT_GRAY_BED, CYAN_BED, PURPLE_BED, BLUE_BED, BROWN_BED, GREEN_BED, RED_BED, BLACK_BED}),
            Map.entry("discs", new Item[]{MUSIC_DISC_5, MUSIC_DISC_11, MUSIC_DISC_13, MUSIC_DISC_CAT, MUSIC_DISC_FAR, MUSIC_DISC_MALL, MUSIC_DISC_STAL, MUSIC_DISC_WAIT, MUSIC_DISC_WARD, MUSIC_DISC_CHIRP, MUSIC_DISC_RELIC, MUSIC_DISC_STRAD, MUSIC_DISC_BLOCKS, MUSIC_DISC_CREATOR, MUSIC_DISC_MELLOHI, MUSIC_DISC_PIGSTEP, MUSIC_DISC_OTHERSIDE, MUSIC_DISC_PRECIPICE, MUSIC_DISC_CREATOR_MUSIC_BOX}),
            Map.entry("goatHorns", new Item[]{GOAT_HORN}),
            Map.entry("patterns", new Item[]{CREEPER_BANNER_PATTERN, FLOW_BANNER_PATTERN, GLOBE_BANNER_PATTERN, GUSTER_BANNER_PATTERN, FLOWER_BANNER_PATTERN, MOJANG_BANNER_PATTERN, PIGLIN_BANNER_PATTERN, SKULL_BANNER_PATTERN}),
            Map.entry("saddles", new Item[]{SADDLE}),
            Map.entry("horseArmor", new Item[]{DIAMOND_HORSE_ARMOR, GOLDEN_HORSE_ARMOR, IRON_HORSE_ARMOR, LEATHER_HORSE_ARMOR}),
            Map.entry("cakes", new Item[]{CAKE}),
            Map.entry("totems", new Item[]{TOTEM_OF_UNDYING})
    );


    @Override
    public void onInitialize() {
        DefaultItemComponentEvents.MODIFY.register(context -> {
            var fields = Config.getClass().getFields();
            for (var field : fields) {
                var key = field.getName();

                try {
                    field.setAccessible(true);
                    var data = (StackItConfig.ItemCategory) field.get(Config);
                    if (!data.enabled) return;

                    var stackSize = data.stackSize;

                    var items = itemsMap.get(key);
                    for (var item : items) {
                        context.modify(item, builder -> {
                            builder.add(DataComponentTypes.MAX_STACK_SIZE, stackSize);
                        });
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

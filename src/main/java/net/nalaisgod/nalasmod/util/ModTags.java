package net.nalaisgod.nalasmod.util;

import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_DETECTABLE_BLOCKS =
                createTag("dowsing_rod_detectable_blocks");
        public static final TagKey<Block> PAXEL_MINEABLE =
                createTag("mineable/paxel");
        public static final TagKey<Block> END_PLANT_REPLACEABLE =
                createTag("end_plant_replaceable");

        public static final TagKey<Block> ORIGINITE_BLOCKS =
                createCommonTag("originite_blocks");
        public static final TagKey<Block> ORIGINITE_ORES =
                createCommonTag("originite_ores");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(NalasMod.MOD_ID, name));

        }

        private static TagKey<Block> createCommonTag(String name) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier("c", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ORIGINITE_INGOTS = createCommonTag("originite_ingots");
        public static final TagKey<Item> ORIGINITE_NUGGETS = createCommonTag("originite_nuggets");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(NalasMod.MOD_ID, name));
        }

        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier("c", name));
        }
    }
}
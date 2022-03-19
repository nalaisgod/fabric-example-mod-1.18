package net.nalaisgod.nalasmod.util;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.nalaisgod.nalasmod.NalasMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final Tag.Identified<Block> DOWSING_ROD_DETECTABLE_BLOCKS =
                createTag("dowsing_rod_detectable_blocks");
        public static final Tag.Identified<Block> PAXEL_MINEABLE =
                createTag("mineable/paxel");

        public static final Tag.Identified<Block> ORIGINITE_BLOCKS =
                createCommonTag("originite_blocks");
        public static final Tag.Identified<Block> ORIGINITE_ORES =
                createCommonTag("originite_ores");


        private static Tag.Identified<Block> createTag(String name) {
            return TagFactory.BLOCK.create(new Identifier(NalasMod.MOD_ID, name));

        }

        private static Tag.Identified<Block> createCommonTag(String name) {
            return TagFactory.BLOCK.create(new Identifier("c", name));
        }
    }

    public static class Items {
        public static final Tag.Identified<Item> ORIGINITE_INGOTS = createCommonTag("originite_ingots");
        public static final Tag.Identified<Item> ORIGINITE_NUGGETS = createCommonTag("originite_nuggets");

        private static Tag.Identified<Item> createTag(String name) {
            return TagFactory.ITEM.create(new Identifier(NalasMod.MOD_ID, name));
        }

        private static Tag.Identified<Item> createCommonTag(String name) {
            return TagFactory.ITEM.create(new Identifier("c", name));
        }
    }
}
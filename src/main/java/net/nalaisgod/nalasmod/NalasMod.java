package net.nalaisgod.nalasmod;

import net.fabricmc.api.ModInitializer;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.enchantment.ModEnchantments;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.util.ModRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NalasMod implements ModInitializer {
	public static final String MOD_ID = "nalasmod";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModEnchantments.registerModEnchantments();

		ModRegistries.registerModStuffs();

	}
}

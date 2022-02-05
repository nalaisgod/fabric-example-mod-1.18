package net.nalaisgod.nalasmod;

import net.fabricmc.api.ModInitializer;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.LogManager;

public class NalasMod implements ModInitializer {
	public static final String MOD_ID = "nalasmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

	}
}

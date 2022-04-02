package net.nalaisgod.nalasmod;

import net.fabricmc.api.ModInitializer;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.block.entity.ModBlockEntities;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.enchantment.ModEnchantments;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.painting.ModPaintings;
import net.nalaisgod.nalasmod.potion.ModPotions;
import net.nalaisgod.nalasmod.recipe.ModRecipes;
import net.nalaisgod.nalasmod.util.ModLootTableModifiers;
import net.nalaisgod.nalasmod.util.ModRegistries;
import net.nalaisgod.nalasmod.villager.ModVillagers;
import net.nalaisgod.nalasmod.world.feature.ModConfiguredFeatures;
import net.nalaisgod.nalasmod.world.gen.ModWorldGen;
import net.nalaisgod.nalasmod.world.structures.ModStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

public class NalasMod implements ModInitializer {
	public static final String MOD_ID = "nalasmod";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfiguredFeatures.registerConfiguredFeatures();


		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerAllEntities();

		ModEnchantments.registerModEnchantments();

		ModLootTableModifiers.modifyLootTables();

		ModRecipes.register();

		ModRegistries.registerModStuffs();
		ModPaintings.registerPaintings();

		ModWorldGen.generateModWorldGen();

		ModEffects.registerEffects();
		ModPotions.registerPotions();

		ModStructures.registerStructureFeatures();

		ModVillagers.setupPOIs();

		GeckoLib.initialize();


	}
}

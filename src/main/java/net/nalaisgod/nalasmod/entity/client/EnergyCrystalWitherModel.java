// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLibMod
// Paste this class into your mod and follow the documentation for GeckoLibMod to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.entity.CrystalEntityDragon;
import net.nalaisgod.nalasmod.block.entity.CrystalEntityWither;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EnergyCrystalWitherModel extends AnimatedGeoModel<CrystalEntityWither> {
	@Override
	public Identifier getAnimationFileLocation(CrystalEntityWither entity) {
		return new Identifier(NalasMod.MOD_ID, "animations/energy_crystal.animation.json");
	}

	@Override
	public Identifier getModelLocation(CrystalEntityWither animatable) {
		return new Identifier(NalasMod.MOD_ID, "geo/energy_crystal.geo.json");
	}

	@Override
	public Identifier getTextureLocation(CrystalEntityWither entity) {
		return new Identifier(NalasMod.MOD_ID, "textures/block/energy_crystal_wither.png");
	}
}
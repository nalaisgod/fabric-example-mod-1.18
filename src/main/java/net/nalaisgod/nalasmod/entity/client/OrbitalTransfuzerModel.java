// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLibMod
// Paste this class into your mod and follow the documentation for GeckoLibMod to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.entity.OrbitalTransfuzerEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OrbitalTransfuzerModel extends AnimatedGeoModel<OrbitalTransfuzerEntity> {
	@Override
	public Identifier getAnimationFileLocation(OrbitalTransfuzerEntity entity) {
		return new Identifier(NalasMod.MOD_ID, "animations/orbital_transfuzer.animation.json");
	}

	@Override
	public Identifier getModelLocation(OrbitalTransfuzerEntity animatable) {
		return new Identifier(NalasMod.MOD_ID, "geo/orbital_transfuzer.geo.json");
	}

	@Override
	public Identifier getTextureLocation(OrbitalTransfuzerEntity entity) {
		return new Identifier(NalasMod.MOD_ID, "textures/block/orbital_transfuzer.png");
	}
}
package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.item.custom.ModFunnyItem;
import software.bernie.example.item.PistolItem;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GunModel extends AnimatedGeoModel<ModFunnyItem> {
	@Override
	public Identifier getModelLocation(ModFunnyItem object) {
		return new Identifier(GeckoLib.ModID, "geo/gun.geo.json");
	}

	@Override
	public Identifier getTextureLocation(ModFunnyItem object) {
		return new Identifier(GeckoLib.ModID, "textures/item/gun.png");
	}

	@Override
	public Identifier getAnimationFileLocation(ModFunnyItem animatable) {
		return new Identifier(GeckoLib.ModID, "animations/gun.animation.json");
	}
}

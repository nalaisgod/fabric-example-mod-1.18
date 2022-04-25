package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.projectile.DeathBomb;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DeathBombModel extends AnimatedGeoModel<DeathBomb> {
	@Override
	public Identifier getModelLocation(DeathBomb object) {
		return new Identifier(NalasMod.MOD_ID, "geo/deathbomb.geo.json");
	}

	@Override
	public Identifier getTextureLocation(DeathBomb object) {
		return new Identifier(NalasMod.MOD_ID, "textures/entity/projectiles/deathbomb.png");
	}

	@Override
	public Identifier getAnimationFileLocation(DeathBomb animatable) {
		return new Identifier(NalasMod.MOD_ID, "animations/deathbomb.animation.json");
	}

}

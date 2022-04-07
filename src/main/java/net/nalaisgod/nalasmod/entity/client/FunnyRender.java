package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.nalaisgod.nalasmod.entity.projectile.FunnyShot;
import software.bernie.example.client.model.entity.RocketModel;
import software.bernie.example.entity.RocketProjectile;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class FunnyRender extends GeoProjectilesRenderer<FunnyShot> {

	public FunnyRender(EntityRendererFactory.Context renderManagerIn) {
		super(renderManagerIn, new FunnyModel());
	}

	protected int getBlockLight(FunnyShot entityIn, BlockPos partialTicks) {
		return 15;
	}

	@Override
	public RenderLayer getRenderType(FunnyShot animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
}

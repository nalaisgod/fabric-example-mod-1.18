package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.block.entity.CrystalEntity;
import net.nalaisgod.nalasmod.block.entity.CrystalEntityDragon;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EnergyCrystalDragonRenderer extends GeoBlockRenderer<CrystalEntityDragon> {
	public EnergyCrystalDragonRenderer() {
		super(new EnergyCrystalDragonModel());
	}

	@Override
	public RenderLayer getRenderType(CrystalEntityDragon animatable, float partialTicks, MatrixStack stack,
									 VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
									 Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
}

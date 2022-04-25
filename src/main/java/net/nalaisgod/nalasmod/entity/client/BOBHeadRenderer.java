package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.block.entity.BOBHeadEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class BOBHeadRenderer extends GeoBlockRenderer<BOBHeadEntity> {
	public BOBHeadRenderer() {
		super(new BOBHeadModel());
	}

	@Override
	public RenderLayer getRenderType(BOBHeadEntity animatable, float partialTicks, MatrixStack stack,
									 VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
									 Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
}

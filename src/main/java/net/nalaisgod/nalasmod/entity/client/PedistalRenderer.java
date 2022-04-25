package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.entity.custom.PedistalEntity;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class PedistalRenderer extends GeoBlockRenderer<PedistalEntity> {
	public PedistalRenderer() {
		super(new PedistalModel());
	}

	@Override
	public RenderLayer getRenderType(PedistalEntity animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
}

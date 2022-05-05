package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import net.nalaisgod.nalasmod.entity.mob.EvokerKingEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class EvokingRenderer extends GeoEntityRenderer<EvokerKingEntity> {
    public EvokingRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new EvokingModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(EvokerKingEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/evoker_king/evoker_king.png");
    }

}

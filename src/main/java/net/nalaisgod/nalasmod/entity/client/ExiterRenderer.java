package net.nalaisgod.nalasmod.entity.client;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.custom.TigerEntity;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ExiterRenderer extends GeoEntityRenderer<ExiterEntity> {
    public ExiterRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ExiterModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(ExiterEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/exiter/exiter.png");
    }
}

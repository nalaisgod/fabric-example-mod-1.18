package net.nalaisgod.nalasmod.entity.client;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.custom.TigerEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TigerRenderer extends GeoEntityRenderer<TigerEntity> {
    public TigerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TigerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(TigerEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/tiger/tiger.png");
    }
}

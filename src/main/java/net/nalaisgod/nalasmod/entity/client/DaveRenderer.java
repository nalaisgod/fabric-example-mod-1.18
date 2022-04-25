package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DaveRenderer extends GeoEntityRenderer<DaveEntity> {
    public DaveRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DaveModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(DaveEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/dave/dave.png");
    }

}

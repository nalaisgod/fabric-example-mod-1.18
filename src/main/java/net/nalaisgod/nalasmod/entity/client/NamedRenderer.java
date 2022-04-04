package net.nalaisgod.nalasmod.entity.client;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.ExiterEntity;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NamedRenderer extends GeoEntityRenderer<NamedEntity> {
    public NamedRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new NamedModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(NamedEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/named/named.png");
    }
}

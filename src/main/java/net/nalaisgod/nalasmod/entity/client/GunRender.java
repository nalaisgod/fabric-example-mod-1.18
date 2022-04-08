package net.nalaisgod.nalasmod.entity.client;


import net.nalaisgod.nalasmod.item.custom.ModFunnyItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class GunRender extends GeoItemRenderer<ModFunnyItem> {
    public GunRender() {
        super(new GunModel());
    }

}

package net.nalaisgod.nalasmod.entity.client;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import net.nalaisgod.nalasmod.item.custom.ModStaffItem;
import software.bernie.example.client.model.item.JackInTheBoxModel;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class StaffRenderer extends GeoItemRenderer<ModStaffItem> {
    public StaffRenderer() {
        super(new StaffModel());
    }


}

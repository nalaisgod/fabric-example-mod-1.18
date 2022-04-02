package net.nalaisgod.nalasmod.entity.client.armor;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.item.custom.OriginiteArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OriginiteArmorModel extends AnimatedGeoModel<OriginiteArmorItem> {
    @Override
    public Identifier getModelLocation(OriginiteArmorItem object) {
        return new Identifier(NalasMod.MOD_ID, "geo/originite_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(OriginiteArmorItem object) {
        return new Identifier(NalasMod.MOD_ID, "textures/models/armor/originite_armor_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(OriginiteArmorItem animatable) {
        return new Identifier(NalasMod.MOD_ID, "animations/armor_animation.json");
    }
}

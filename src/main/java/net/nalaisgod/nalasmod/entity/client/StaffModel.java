package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import net.nalaisgod.nalasmod.item.custom.ModStaffItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StaffModel extends AnimatedGeoModel<ModStaffItem> {
    @Override
    public Identifier getModelLocation(ModStaffItem entity) {
        return new Identifier(NalasMod.MOD_ID, "geo/staff_of_the_orb.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ModStaffItem entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/item/staff_of_the_orb.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ModStaffItem entity) {
        return new Identifier(NalasMod.MOD_ID, "animations/staff_of_the_orb.animation.json");
    }
}
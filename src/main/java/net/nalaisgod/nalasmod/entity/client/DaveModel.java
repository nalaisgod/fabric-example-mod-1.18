package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DaveModel extends AnimatedGeoModel<DaveEntity> {
    @Override
    public Identifier getModelLocation(DaveEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "geo/dave.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DaveEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/dave/dave.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DaveEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "animations/dave.animation.json");
    }
}
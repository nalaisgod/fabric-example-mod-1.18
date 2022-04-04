package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NamedModel extends AnimatedGeoModel<NamedEntity> {
    @Override
    public Identifier getModelLocation(NamedEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "geo/named.geo.json");
    }

    @Override
    public Identifier getTextureLocation(NamedEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/named/named.png");
    }

    @Override
    public Identifier getAnimationFileLocation(NamedEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "animations/named.animation.json");
    }

    private static <T extends NamedEntity> void rotateHead(T entity, ModelPart head, int sigma) {
        head.yaw = (entity.getHeadYaw(sigma) - entity.bodyYaw) * ((float)Math.PI / 180);
        head.pitch = entity.getHeadPitch(sigma) * ((float)Math.PI / 180);
    }
}
package net.nalaisgod.nalasmod.entity.client;

import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.entity.custom.TigerEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class TigerModel extends AnimatedGeoModel<TigerEntity> {
    @Override
    public Identifier getModelLocation(TigerEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "geo/tiger.geo.json");
    }

    @Override
    public Identifier getTextureLocation(TigerEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "textures/entity/tiger/tiger.png");
    }

    @Override
    public Identifier getAnimationFileLocation(TigerEntity entity) {
        return new Identifier(NalasMod.MOD_ID, "animations/tiger.animation.json");
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(TigerEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        }
    }
}
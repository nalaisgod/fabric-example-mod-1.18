package net.nalaisgod.nalasmod.entity.client.armor;

import net.nalaisgod.nalasmod.item.custom.OriginiteArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class OriginiteArmorRenderer extends GeoArmorRenderer<OriginiteArmorItem> {
    public OriginiteArmorRenderer() {
        super(new OriginiteArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}

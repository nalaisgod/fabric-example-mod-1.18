package net.nalaisgod.nalasmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;
import net.nalaisgod.nalasmod.item.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.UUID;

public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                     .put(ModArmorMaterials.ORIGINITE,
                             new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 1)).build();


    public ModArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient()) {
            if(entity instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity)entity;

                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                } else {
                    player.setNoGravity(false);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            StatusEffectInstance mapStatusEffect = entry.getValue();


            if(hasCorrectArmorOn(mapArmorMaterial, player) && player.getItemCooldownManager().isCoolingDown(this) == false) {
                player.getItemCooldownManager().set(this, 600);
                player.heal(1);
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
                player.setNoGravity(true);
            }

        }
    }





    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

        if(hasCorrectArmorOn(mapArmorMaterial, player)) {
            player.addStatusEffect(new StatusEffectInstance(mapStatusEffect.getEffectType(),
                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

             if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
                 player.getInventory().damageArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
             }
        }
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack boots = player.getInventory().getArmorStack(0);
        ItemStack leggings = player.getInventory().getArmorStack(1);
        ItemStack breastplate = player.getInventory().getArmorStack(2);
        ItemStack helmet = player.getInventory().getArmorStack(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();

    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack armorStack: player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;

    }
}
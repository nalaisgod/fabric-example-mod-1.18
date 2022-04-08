package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.effect.ModEffects;
import net.nalaisgod.nalasmod.entity.projectile.Bullet;
import net.nalaisgod.nalasmod.entity.projectile.IceArrow;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBulletItem
        extends ArrowItem {
        public ModBulletItem(Settings settings) {
            super(settings);
        }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new Bullet(world, shooter);
    }
}






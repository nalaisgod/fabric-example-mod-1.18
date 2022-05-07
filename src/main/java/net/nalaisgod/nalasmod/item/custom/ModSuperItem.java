package net.nalaisgod.nalasmod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;


public class ModSuperItem extends Item {



    public ModSuperItem(Settings properties) {
        super(properties);


    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        double d = Math.min(user.getY(), user.getY());
        double e = Math.max(user.getY(), user.getY()) + 1.0;
        float f = (float) MathHelper.atan2(user.getZ() - user.getZ(), user.getX() - user.getX());
        float g;
        int i;

        for (i = 0; i < 5; ++i) {
            g = f + (float) i * (float) Math.PI * 0.4f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 1.5, user.getZ() + (double) MathHelper.sin(g) * 1.5, d, e, user);
        }
        for (i = 0; i < 8; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 1.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 2.5, user.getZ() + (double) MathHelper.sin(g) * 2.5, d, e, user);
        }
        for (i = 0; i < 11; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 2.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 3.5, user.getZ() + (double) MathHelper.sin(g) * 3.5, d, e, user);
        }
        for (i = 0; i < 14; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 3.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 4.5, user.getZ() + (double) MathHelper.sin(g) * 4.5, d, e, user);
        }
        for (i = 0; i < 17; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 4.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 5.5, user.getZ() + (double) MathHelper.sin(g) * 5.5, d, e, user);
        }
        for (i = 0; i < 20; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 5.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 6.5, user.getZ() + (double) MathHelper.sin(g) * 6.5, d, e, user);
        }
        for (i = 0; i < 23; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 6.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 7.5, user.getZ() + (double) MathHelper.sin(g) * 7.5, d, e, user);
        }
        for (i = 0; i < 26; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 7.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 8.5, user.getZ() + (double) MathHelper.sin(g) * 8.5, d, e, user);
        }
        for (i = 0; i < 29; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 8.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 9.5, user.getZ() + (double) MathHelper.sin(g) * 9.5, d, e, user);
        }
        for (i = 0; i < 32; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 9.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 10.5, user.getZ() + (double) MathHelper.sin(g) * 10.5, d, e, user);
        }

        user.getItemCooldownManager().set(this, 6000);
        itemStack.damage(1, user, p -> p.sendToolBreakStatus(hand));

        return TypedActionResult.success(itemStack, world.isClient());
    }

    private void conjureFangs(double x, double z, double maxY, double y, PlayerEntity user) {
        BlockPos blockPos = new BlockPos(x, y, z);

        boolean bl = false;
        double d = 0.0;
        do {
            BlockState blockState2;
            VoxelShape voxelShape;
            BlockPos blockPos2;
            BlockState blockState;
            if (!(blockState = user.world.getBlockState(blockPos2 = blockPos.down())).isSideSolidFullSquare(user.world, blockPos2, Direction.UP))
                continue;
            if (!user.world.isAir(blockPos) && !(voxelShape = (blockState2 = user.world.getBlockState(blockPos)).getCollisionShape(user.world, blockPos)).isEmpty()) {
                d = voxelShape.getMax(Direction.Axis.Y);
            }
            bl = true;
            break;
        } while ((blockPos = blockPos.down()).getY() >= MathHelper.floor(maxY) - 1);
        if (bl) {

            user.world.spawnEntity(new TntEntity(user.world, x, (double) blockPos.getY() + d, z, user));
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.nalasmod.super_item.tooltip.shift"));
        } else {
            tooltip.add(new TranslatableText("item.nalasmod.dowsing_rod.tooltip"));
        }
    }


}




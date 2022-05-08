package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.nalaisgod.nalasmod.entity.mob.EvokerKingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModFallenKingBladeItem extends SwordItem {

    public ModFallenKingBladeItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        double d = Math.min(user.getY(), user.getY());
        double e = Math.max(user.getY(), user.getY()) + 1.0;
        float f = (float) MathHelper.atan2(user.getZ() - user.getZ(), user.getX() - user.getX());
            float g;
            int i;
        for (i = 0; i < 2; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f - 0.7433629f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 0.5, user.getZ() + (double) MathHelper.sin(g) * 0.5, d, e, g, 0, user);
        }
        for (i = 0; i < 5; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 0.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 1.5, user.getZ() + (double) MathHelper.sin(g) * 1.5, d, e, g, 0, user);
        }
            for (i = 0; i < 8; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 1.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 2.5, user.getZ() + (double) MathHelper.sin(g) * 2.5, d, e, g, 3, user);
            }
            for (i = 0; i < 11; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 2.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 3.5, user.getZ() + (double) MathHelper.sin(g) * 3.5, d, e, g, 6, user);
            }
            for (i = 0; i < 14; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 3.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 4.5, user.getZ() + (double) MathHelper.sin(g) * 4.5, d, e, g, 9, user);
            }
            for (i = 0; i < 17; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 4.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 5.5, user.getZ() + (double) MathHelper.sin(g) * 5.5, d, e, g, 9, user);
            }
            for (i = 0; i < 20; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 5.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 6.5, user.getZ() + (double) MathHelper.sin(g) * 6.5, d, e, g, 9, user);
            }
            for (i = 0; i < 23; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 6.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 7.5, user.getZ() + (double) MathHelper.sin(g) * 7.5, d, e, g, 9, user);
            }
            for (i = 0; i < 26; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 7.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 8.5, user.getZ() + (double) MathHelper.sin(g) * 8.5, d, e, g, 9, user);
            }
            for (i = 0; i < 29; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 8.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 9.5, user.getZ() + (double) MathHelper.sin(g) * 9.5, d, e, g, 9, user);
            }
            for (i = 0; i < 32; ++i) {
                g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 9.2566371f;
                conjureFangs(user.getX() + (double) MathHelper.cos(g) * 10.5, user.getZ() + (double) MathHelper.sin(g) * 10.5, d, e, g, 9, user);
            }

        user.getItemCooldownManager().set(this, 60);
            itemStack.damage(10, user, p -> p.sendToolBreakStatus(hand));

        return TypedActionResult.success(itemStack, world.isClient());
    }

    private void conjureFangs(double x, double z, double maxY, double y, float yaw, int warmup, PlayerEntity user) {
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
        } while ((blockPos = blockPos.down()).getY() >= MathHelper.floor(maxY) - 10);
        if (bl) {
            user.world.spawnEntity(new EvokerFangsEntity(user.world, x, (double) blockPos.getY() + d, z, yaw, warmup, user));
        }
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.nalasmod.fallen_kings_blade.tooltip.shift"));
        } else {
            tooltip.add(new TranslatableText("item.nalasmod.dowsing_rod.tooltip"));
        }
    }


}

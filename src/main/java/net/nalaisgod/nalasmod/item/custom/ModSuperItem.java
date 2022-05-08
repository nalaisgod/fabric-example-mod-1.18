package net.nalaisgod.nalasmod.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.item.ModItems;
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


public class ModSuperItem extends RangedWeaponItem {
    public static final Predicate<ItemStack> CEPHALOPOD = stack -> stack.isOf(ModItems.CHOCOLATE_COVERED_CEPHALOPOD);



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
        boolean bl;
        boolean bl2 = bl = !user.getArrowType(itemStack).isEmpty();
        double d = Math.min(user.getY(), user.getY());
        double e = Math.max(user.getY(), user.getY()) + 1.0;
        float f = (float) MathHelper.atan2(user.getZ() - user.getZ(), user.getX() - user.getX());
        if (user.getAbilities().creativeMode || bl) {
        float g;
        int i;
        user.getItemCooldownManager().set(this, 600);

        for (i = 0; i < 5; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 5.0f + 1.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 1.5, user.getZ() + (double) MathHelper.sin(g) * 1.5, d, e, user);
        }
        for (i = 0; i < 8; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 8.0f + 1.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 2.5, user.getZ() + (double) MathHelper.sin(g) * 2.5, d, e, user);
        }
        for (i = 0; i < 11; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 11.0f + 1.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 3.5, user.getZ() + (double) MathHelper.sin(g) * 3.5, d, e, user);
        }
        for (i = 0; i < 14; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 14.0f + 1.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 4.5, user.getZ() + (double) MathHelper.sin(g) * 4.5, d, e, user);
        }
        for (i = 0; i < 17; ++i) {
            g = f + (float) i * (float) Math.PI * 2.0f / 17.0f + 1.2566371f;
            conjureFangs(user.getX() + (double) MathHelper.cos(g) * 5.5, user.getZ() + (double) MathHelper.sin(g) * 5.5, d, e, user);
        }

            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }


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

            user.world.spawnEntity(new EndCrystalEntity(user.world, x, (double) blockPos.getY() + d, z));
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        boolean bl2;
        int i;
        float f;
        if (!(user instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)user;
        boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack itemStack = playerEntity.getArrowType(stack);
        if (itemStack.isEmpty() && !bl) {
            return;
        }
        if (itemStack.isEmpty()) {
            itemStack = new ItemStack(ModItems.CHOCOLATE_COVERED_CEPHALOPOD);
        }
        boolean bl3 = bl2 = bl && itemStack.isOf(ModItems.CHOCOLATE_COVERED_CEPHALOPOD);

        if (!bl2 && !playerEntity.getAbilities().creativeMode) {
            itemStack.decrement(1);
            if (itemStack.isEmpty()) {
                playerEntity.getInventory().removeOne(itemStack);
            }
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



    @Override
    public Predicate<ItemStack> getProjectiles() {
        return CEPHALOPOD;
    }

    @Override
    public int getRange() {
        return 15;
    }


}




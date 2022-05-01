package net.nalaisgod.nalasmod.block.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class ModSummonWither extends Block implements BlockEntityProvider {
    @Nullable
    private static BlockPattern witherBossPattern;


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.ENERGY_CRYSTAL_WITHER.instantiate(pos, state);
    }


    public ModSummonWither(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        ModSummonWither.onPlaced(world, pos);
    }

    public static void onPlaced(World world, BlockPos pos) {
        if (world.isClient) {
            return;
        }

        if (world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        BlockPattern blockPattern = ModSummonWither.getWitherBossPattern();
        BlockPattern.Result result = blockPattern.searchAround(world, pos);
        if (result == null) {
            return;
        }
        for (int i = 0; i < blockPattern.getWidth(); ++i) {
            for (int j = 0; j < blockPattern.getHeight(); ++j) {
                CachedBlockPosition cachedBlockPosition = result.translate(i, j, 0);
                world.setBlockState(cachedBlockPosition.getBlockPos(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, cachedBlockPosition.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition.getBlockState()));
            }
        }
        WitherEntity witherEntity = EntityType.WITHER.create(world);
        BlockPos blockPos = result.translate(1, 2, 0).getBlockPos();
        witherEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.55, (double)blockPos.getZ() + 0.5, result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f, 0.0f);
        witherEntity.bodyYaw = result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f;
        witherEntity.onSummoned();
        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, witherEntity.getBoundingBox().expand(500.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, witherEntity);
        }
        world.spawnEntity(witherEntity);
        for (int k = 0; k < blockPattern.getWidth(); ++k) {
            for (int l = 0; l < blockPattern.getHeight(); ++l) {
                world.updateNeighbors(result.translate(k, l, 0).getBlockPos(), Blocks.AIR);
            }
        }
    }


    private static BlockPattern getWitherBossPattern() {
        if (witherBossPattern == null) {
            witherBossPattern = BlockPatternBuilder.start().aisle("^", "#").where('#', pos -> pos.getBlockState().isOf(ModBlocks.PEDISTAL)).where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.ENERGY_CRYSTAL_WITHER))).build();
        }
        return witherBossPattern;
    }
}

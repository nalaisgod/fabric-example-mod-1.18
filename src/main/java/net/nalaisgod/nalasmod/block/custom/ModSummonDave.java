package net.nalaisgod.nalasmod.block.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.util.function.MaterialPredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.entity.mob.NamedEntity;
import net.nalaisgod.nalasmod.util.TileRegistry;
import org.jetbrains.annotations.Nullable;

/**
 * Access widened by fabric-transitive-access-wideners-v1 to accessible
 */
public class ModSummonDave
        extends FacingBlock implements BlockEntityProvider {
    @Nullable
    private static BlockPattern namedBossPattern;


    public ModSummonDave(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
            ModSummonDave.onPlaced(world, pos);
    }

    public static void onPlaced(World world, BlockPos pos) {
        if (world.isClient) {
            return;
        }

        if (world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        BlockPattern blockPattern = ModSummonDave.getNamedBossPattern();
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
        NamedEntity namedEntity = ModEntities.NAMED.create(world);
        BlockPos blockPos = result.translate(1, 2, 0).getBlockPos();
        namedEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.55, (double)blockPos.getZ() + 0.5, result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f, 0.0f);
        namedEntity.bodyYaw = result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f;
        namedEntity.onSummoned();
        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, namedEntity.getBoundingBox().expand(500.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, namedEntity);
        }
        world.spawnEntity(namedEntity);
        for (int k = 0; k < blockPattern.getWidth(); ++k) {
            for (int l = 0; l < blockPattern.getHeight(); ++l) {
                world.updateNeighbors(result.translate(k, l, 0).getBlockPos(), Blocks.AIR);
            }
        }
    }


    private static BlockPattern getNamedBossPattern() {
        if (namedBossPattern == null) {
            namedBossPattern = BlockPatternBuilder.start().aisle("^", "#").where('#', pos -> pos.getBlockState().isOf(ModBlocks.PEDISTAL)).where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.ENERGY_CRYSTAL))).build();
        }
        return namedBossPattern;
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getPlayerLookDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return TileRegistry.PEDISTAL.instantiate(pos, state);
    }


}

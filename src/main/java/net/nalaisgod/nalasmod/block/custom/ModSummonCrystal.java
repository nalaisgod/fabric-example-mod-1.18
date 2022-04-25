package net.nalaisgod.nalasmod.block.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import org.jetbrains.annotations.Nullable;

public class ModSummonCrystal extends Block{
    @Nullable
    private static BlockPattern daveBossPattern;


    public ModSummonCrystal(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        ModSummonCrystal.onPlaced(world, pos);
    }

    public static void onPlaced(World world, BlockPos pos) {
        if (world.isClient) {
            return;
        }

        if (world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        BlockPattern blockPattern = ModSummonCrystal.getDaveBossPattern();
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
        DaveEntity daveEntity = ModEntities.DAVE.create(world);
        BlockPos blockPos = result.translate(1, 2, 0).getBlockPos();
        daveEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.55, (double)blockPos.getZ() + 0.5, result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f, 0.0f);
        daveEntity.bodyYaw = result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f;
        daveEntity.onSummoned();
        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, daveEntity.getBoundingBox().expand(500.0))) {
            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, daveEntity);
        }
        world.spawnEntity(daveEntity);
        for (int k = 0; k < blockPattern.getWidth(); ++k) {
            for (int l = 0; l < blockPattern.getHeight(); ++l) {
                world.updateNeighbors(result.translate(k, l, 0).getBlockPos(), Blocks.AIR);
            }
        }
    }


    private static BlockPattern getDaveBossPattern() {
        if (daveBossPattern == null) {
            daveBossPattern = BlockPatternBuilder.start().aisle("^", "#").where('#', pos -> pos.getBlockState().isOf(ModBlocks.PEDISTAL)).where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.ENERGY_CRYSTAL))).build();
        }
        return daveBossPattern;
    }
}

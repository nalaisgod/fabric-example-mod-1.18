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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.block.entity.ModBlockEntities;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.entity.mob.DaveEntity;
import org.jetbrains.annotations.Nullable;

/**
 * Access widened by fabric-transitive-access-wideners-v1 to accessible
 */
public class ModSummonDave
        extends FacingBlock implements BlockEntityProvider {

    public ModSummonDave(Settings settings) {
        super(settings);
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
        return ModBlockEntities.PEDISTAL.instantiate(pos, state);
    }


}

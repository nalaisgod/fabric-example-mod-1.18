package net.nalaisgod.nalasmod.block.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.block.entity.ModBlockEntities;
import net.nalaisgod.nalasmod.entity.ModEntities;
import net.nalaisgod.nalasmod.entity.mob.EvokerKingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class ModCursedTotemBlock extends Block  {
    @Nullable
    private static BlockPattern witherBossPattern;




    public ModCursedTotemBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        ModCursedTotemBlock.onPlaced(world, pos);
    }

    public static void onPlaced(World world, BlockPos pos) {
        if (world.isClient) {
            return;
        }

        if (world.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        BlockPattern blockPattern = ModCursedTotemBlock.getWitherBossPattern();
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
        EvokerKingEntity witherEntity = ModEntities.EVOKER_KING.create(world);
        BlockPos blockPos = result.translate(1, 2, 0).getBlockPos();
        witherEntity.refreshPositionAndAngles((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.55, (double)blockPos.getZ() + 0.5, result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f, 0.0f);
        witherEntity.bodyYaw = result.getForwards().getAxis() == Direction.Axis.X ? 0.0f : 90.0f;
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
            witherBossPattern = BlockPatternBuilder.start().aisle("^", "#").where('#', pos -> pos.getBlockState().isOf(Blocks.EMERALD_BLOCK)).where('^', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(ModBlocks.CURSED_TOTEM))).build();
        }
        return witherBossPattern;
    }


    private static final VoxelShape SHAPE =     Stream.of(
            Block.createCuboidShape(1, 6, 6, 15, 8, 10),
            Block.createCuboidShape(6, 0, 6, 10, 1, 10),
            Block.createCuboidShape(5, 14, 6, 11, 15, 10),
            Block.createCuboidShape(5, 1, 6, 11, 3, 10),
            Block.createCuboidShape(4, 3, 6, 12, 5, 10),
            Block.createCuboidShape(4, 8, 6, 12, 14, 10),
            Block.createCuboidShape(2, 5, 6, 14, 6, 10)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
                return SHAPE;
    }


}

package net.nalaisgod.nalasmod.block.custom;

import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import net.nalaisgod.nalasmod.world.feature.tree.SoulBlossomSaplingGenerator;

public class ModSaplingBlock extends PlantBlock implements Fertilizable {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 9.0, 12.0);
    private static final double GROW_CHANCE = 0.4;
    private final Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> feature;




    public ModSaplingBlock(AbstractBlock.Settings settings, Supplier<ConfiguredFeature<HugeFungusFeatureConfig, ?>> feature) {
        super(settings);
        this.feature = feature;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.END_STONE) || floor.isOf(Blocks.SOUL_SOIL) || floor.isOf(Blocks.SOUL_SAND) || super.canPlantOnTop(floor, world, pos);
    }


        @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }


    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)random.nextFloat() < 0.4;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.feature.get().generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
    }
}

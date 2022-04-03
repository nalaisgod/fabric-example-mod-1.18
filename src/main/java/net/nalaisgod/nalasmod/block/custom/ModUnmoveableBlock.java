package net.nalaisgod.nalasmod.block.custom;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;


public class ModUnmoveableBlock
        extends Block {
public ModUnmoveableBlock(AbstractBlock.Settings settings) {
        super(settings);
    }
    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }
}
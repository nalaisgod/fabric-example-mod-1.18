package net.nalaisgod.nalasmod.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;

public class ModGlassBlock
        extends AbstractGlassBlock {
    public ModGlassBlock(AbstractBlock.Settings settings) {
        super(settings);
    }
    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }
}

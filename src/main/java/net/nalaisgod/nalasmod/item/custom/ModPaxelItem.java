package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModPaxelItem extends MiningToolItem {
    public ModPaxelItem(ToolMaterial material,float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.nalasmod.paxel.tooltip.shift"));
        } else {
            tooltip.add(new TranslatableText("item.nalasmod.dowsing_rod.tooltip"));
        }
    }
}

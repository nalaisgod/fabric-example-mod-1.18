package net.nalaisgod.nalasmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.Tag;
import net.nalaisgod.nalasmod.util.ModTags;

public class ModPaxelItem extends MiningToolItem {
    public ModPaxelItem(ToolMaterial material,float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.PAXEL_MINEABLE, settings);
    }
}

package net.nalaisgod.nalasmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.nalaisgod.nalasmod.NalasMod;

public class ModItemGroup {
    public static final ItemGroup ORIGINITE = FabricItemGroupBuilder.build(new Identifier(NalasMod.MOD_ID, "originite"),
            () -> new ItemStack(ModItems.ORIGINITE_INGOT));
}

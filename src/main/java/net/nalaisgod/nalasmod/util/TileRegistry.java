package net.nalaisgod.nalasmod.util;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;
import net.nalaisgod.nalasmod.entity.custom.PedistalEntity;
import software.bernie.example.block.tile.BotariumTileEntity;
import software.bernie.example.block.tile.FertilizerTileEntity;
import software.bernie.example.registry.BlockRegistry;
import software.bernie.geckolib3.GeckoLib;

public class TileRegistry {
	public static final BlockEntityType<PedistalEntity> PEDISTAL = Registry.register(
			Registry.BLOCK_ENTITY_TYPE, NalasMod.MOD_ID + ":pedistal",
			FabricBlockEntityTypeBuilder.create(PedistalEntity::new, ModBlocks.PEDISTAL).build(null));
}

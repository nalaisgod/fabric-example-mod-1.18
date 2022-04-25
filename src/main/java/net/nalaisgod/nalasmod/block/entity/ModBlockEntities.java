package net.nalaisgod.nalasmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nalaisgod.nalasmod.NalasMod;
import net.nalaisgod.nalasmod.block.ModBlocks;

public class ModBlockEntities {
    public static BlockEntityType<OriginiteBlasterEntity> ORIGINITE_BLASTER;
    public static BlockEntityType<PedistalEntity> PEDISTAL;

    public static void registerAllEntities() {
        ORIGINITE_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "originite_blaster"),
                FabricBlockEntityTypeBuilder.create(OriginiteBlasterEntity::new,
                        ModBlocks.ORIGINITE_BLASTER).build(null));
        PEDISTAL = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "pedistal"),
                FabricBlockEntityTypeBuilder.create(PedistalEntity::new,
                        ModBlocks.PEDISTAL).build(null));
    }



}

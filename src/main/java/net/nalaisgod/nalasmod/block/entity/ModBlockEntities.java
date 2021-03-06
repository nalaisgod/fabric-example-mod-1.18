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
    public static BlockEntityType<BOBHeadEntity> BOB_HEAD;
    public static BlockEntityType<CrystalEntity> ENERGY_CRYSTAL;
    public static BlockEntityType<CrystalEntityDragon> ENERGY_CRYSTAL_DRAGON;
    public static BlockEntityType<CrystalEntityWither> ENERGY_CRYSTAL_WITHER;
    public static BlockEntityType<OrbitalTransfuzerEntity> ORBITAL_TRANSFUZER;

    public static void registerAllEntities() {
        ORIGINITE_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "originite_blaster"),
                FabricBlockEntityTypeBuilder.create(OriginiteBlasterEntity::new,
                        ModBlocks.ORIGINITE_BLASTER).build(null));
        PEDISTAL = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "pedistal"),
                FabricBlockEntityTypeBuilder.create(PedistalEntity::new,
                        ModBlocks.PEDISTAL).build(null));
        BOB_HEAD = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "bob_head"),
                FabricBlockEntityTypeBuilder.create(BOBHeadEntity::new,
                        ModBlocks.BOB_HEAD).build(null));
        ENERGY_CRYSTAL = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "energy_crystal"),
                FabricBlockEntityTypeBuilder.create(CrystalEntity::new,
                        ModBlocks.ENERGY_CRYSTAL).build(null));
        ENERGY_CRYSTAL_DRAGON = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "energy_crystal_dragon"),
                FabricBlockEntityTypeBuilder.create(CrystalEntityDragon::new,
                        ModBlocks.ENERGY_CRYSTAL_DRAGON).build(null));
        ENERGY_CRYSTAL_WITHER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "energy_crystal_wither"),
                FabricBlockEntityTypeBuilder.create(CrystalEntityWither::new,
                        ModBlocks.ENERGY_CRYSTAL_WITHER).build(null));
        ORBITAL_TRANSFUZER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(NalasMod.MOD_ID, "orbital_transfuzer"),
                FabricBlockEntityTypeBuilder.create(OrbitalTransfuzerEntity::new,
                        ModBlocks.ORBITAL_TRANSFUZER).build(null));
    }



}

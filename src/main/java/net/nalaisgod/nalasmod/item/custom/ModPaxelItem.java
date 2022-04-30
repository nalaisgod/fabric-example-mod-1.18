package net.nalaisgod.nalasmod.item.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.nalaisgod.nalasmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ModPaxelItem extends MiningToolItem {
    protected static final Map<Block, Block> STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>().put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM).put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE).put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM).put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE).build();
    protected static final Map<Block, BlockState> PATH_STATES = Maps.newHashMap(new ImmutableMap.Builder<Block, BlockState>().put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.getDefaultState()).put(Blocks.DIRT, Blocks.DIRT_PATH.getDefaultState()).put(Blocks.PODZOL, Blocks.DIRT_PATH.getDefaultState()).put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.getDefaultState()).put(Blocks.MYCELIUM, Blocks.DIRT_PATH.getDefaultState()).put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.getDefaultState()).build());

    
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
    

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<BlockState> strippable = this.getStrippedState(blockState);
        Optional<BlockState> oxi = Oxidizable.getDecreasedOxidationState(blockState);
        Optional<BlockState> wax = Optional.ofNullable((Block)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(blockState.getBlock())).map(block -> block.getStateWithProperties(blockState));
        ItemStack itemStack = context.getStack();
        Optional<Object> main = Optional.empty();
        BlockState blockState2 = PATH_STATES.get(blockState.getBlock());
        BlockState blockState3 = null;

        if (strippable.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else if (oxi.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
        } else if (wax.isPresent()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
        }
        if (main.isPresent()) {
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
            }
            world.setBlockState(blockPos, (BlockState)main.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            if (playerEntity != null) {
                itemStack.damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
            }
            return ActionResult.success(world.isClient);
        }

            if (blockState2 != null && world.getBlockState(blockPos.up()).isAir()) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                blockState3 = blockState2;
            } else if (blockState.getBlock() instanceof CampfireBlock && blockState.get(CampfireBlock.LIT).booleanValue()) {
                if (!world.isClient()) {
                    world.syncWorldEvent(null, WorldEvents.FIRE_EXTINGUISHED, blockPos, 0);
                }
                CampfireBlock.extinguish(context.getPlayer(), world, blockPos, blockState);
                blockState3 = (BlockState)blockState.with(CampfireBlock.LIT, false);
            }
            if (blockState3 != null) {
                if (!world.isClient) {
                    world.setBlockState(blockPos, blockState3, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    if (playerEntity != null) {
                        context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                    }
                }
                return ActionResult.success(world.isClient);
            }
        return ActionResult.PASS;
    }


    private Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable(STRIPPED_BLOCKS.get(state.getBlock())).map(block -> (BlockState)block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
    }
}

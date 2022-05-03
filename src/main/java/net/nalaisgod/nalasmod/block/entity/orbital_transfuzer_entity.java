package net.nalaisgod.nalasmod.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.item.inventory.ImplementedInventory;
import net.nalaisgod.nalasmod.recipe.OriginiteBlasterRecipe;
import net.nalaisgod.nalasmod.screen.OrbitalTransfuzerScreenHandler;
import net.nalaisgod.nalasmod.screen.OriginiteBlasterScreenHandler;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Optional;

public class orbital_transfuzer_entity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, IAnimatable {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(10, ItemStack.EMPTY);
    private final AnimationFactory factory = new AnimationFactory(this);

    public orbital_transfuzer_entity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORBITAL_TRANSFUZER, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Orbital Transfuzer");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new OrbitalTransfuzerScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public static void tick(World world, BlockPos pos, BlockState state, orbital_transfuzer_entity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    private static void craftItem(orbital_transfuzer_entity entity) {
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);
        entity.removeStack(3, 1);
        entity.removeStack(4, 1);
        entity.removeStack(5, 1);
        entity.removeStack(6, 1);
        entity.removeStack(7, 1);
        entity.removeStack(8, 1);

        entity.setStack(9, new ItemStack(ModItems.ORIGINITE_INGOT,
                entity.getStack(9).getCount() + 1));
    }

    private static boolean hasRecipe(orbital_transfuzer_entity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == Items.DIAMOND;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == Items.ANCIENT_DEBRIS;
        boolean hasItemInThirdSlot = entity.getStack(2).getItem() == Items.EMERALD;
        boolean hasItemInFourthSlot = entity.getStack(3).getItem() == Items.LAPIS_LAZULI;
        boolean hasItemInFifthSlot = entity.getStack(4).getItem() == Items.REDSTONE;
        boolean hasItemInSixthSlot = entity.getStack(5).getItem() == Items.RAW_COPPER;
        boolean hasItemInSeventhSlot = entity.getStack(6).getItem() == Items.RAW_GOLD;
        boolean hasItemInEighthSlot = entity.getStack(7).getItem() == Items.RAW_IRON;
        boolean hasItemInNinthSlot = entity.getStack(8).getItem() == Items.COAL;

        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot && hasItemInFourthSlot &&
                hasItemInFifthSlot && hasItemInSixthSlot && hasItemInSeventhSlot && hasItemInEighthSlot && hasItemInNinthSlot;
    }

    private static boolean hasNotReachedStackLimit(orbital_transfuzer_entity entity) {
        return entity.getStack(9).getCount() < entity.getStack(9).getMaxCount();
    }






    @SuppressWarnings("unchecked")
    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.orbital_transfuzer.open", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(
                new AnimationController<orbital_transfuzer_entity>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}

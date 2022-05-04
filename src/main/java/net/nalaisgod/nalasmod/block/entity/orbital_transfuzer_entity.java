package net.nalaisgod.nalasmod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.item.ModItems;
import net.nalaisgod.nalasmod.item.inventory.ImplementedInventory;
import net.nalaisgod.nalasmod.screen.OrbitalTransfuzerScreenHandler;
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
            DefaultedList.ofSize(11, ItemStack.EMPTY);
    private final AnimationFactory factory = new AnimationFactory(this);


    public orbital_transfuzer_entity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORBITAL_TRANSFUZER, pos, state);

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
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
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }


    public static void tick(World world, BlockPos pos, BlockState state, orbital_transfuzer_entity entity) {
        if(hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    
    


    private static void craftItem(orbital_transfuzer_entity entity) {

            entity.removeStack(0,1);
            entity.removeStack(1,1);
            entity.removeStack(2,1);
            entity.removeStack(3,1);
            entity.removeStack(4,1);
            entity.removeStack(5,1);
            entity.removeStack(6,1);
            entity.removeStack(7,1);
            entity.removeStack(8,1);
            entity.removeStack(9,1);

            entity.setStack(10, new ItemStack(ModItems.ORIGINITE_INGOT,
                    entity.getStack(10).getCount() + 1));

    }

    private static boolean hasRecipe(orbital_transfuzer_entity entity) {
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.RAW_ORIGINITE;
        boolean hasItemInNinthSlot = entity.getStack(1).getItem() == Items.COAL;
        boolean hasItemInSeventhSlot = entity.getStack(2).getItem() == Items.RAW_GOLD;
        boolean hasItemInThirdSlot = entity.getStack(3).getItem() == Items.EMERALD;
        boolean hasItemInEighthSlot = entity.getStack(4).getItem() == Items.RAW_IRON;
        boolean hasItemInFourthSlot = entity.getStack(5).getItem() == Items.LAPIS_LAZULI;
        boolean hasItemInSixthSlot = entity.getStack(6).getItem() == Items.RAW_COPPER;
        boolean hasItemInFifthSlot = entity.getStack(7).getItem() == Items.REDSTONE;
        boolean hasItemInSecondSlot = entity.getStack(8).getItem() == Items.DIAMOND;
        boolean hasItemInTenthSlot = entity.getStack(9).getItem() == Items.ANCIENT_DEBRIS;

        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot && hasItemInFourthSlot &&
                hasItemInFifthSlot && hasItemInSixthSlot && hasItemInSeventhSlot && hasItemInEighthSlot &&
                hasItemInNinthSlot && hasItemInTenthSlot;

    }

    private static boolean hasNotReachedStackLimit(orbital_transfuzer_entity entity) {
        return entity.getStack(9).getCount() < entity.getStack(9).getMaxCount();
    }



    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(10).getItem() == output.getItem() || inventory.getStack(10).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(10).getMaxCount() > inventory.getStack(10).getCount();
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

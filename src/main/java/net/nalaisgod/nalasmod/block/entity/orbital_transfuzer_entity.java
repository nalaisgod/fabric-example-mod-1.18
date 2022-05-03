package net.nalaisgod.nalasmod.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.item.inventory.ImplementedInventory;
import net.nalaisgod.nalasmod.recipe.OriginiteBlasterRecipe;
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
            DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final AnimationFactory factory = new AnimationFactory(this);




    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 1200;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public orbital_transfuzer_entity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORBITAL_TRANSFUZER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return orbital_transfuzer_entity.this.progress;
                    case 1: return orbital_transfuzer_entity.this.maxProgress;
                    case 2: return orbital_transfuzer_entity.this.fuelTime;
                    case 3: return orbital_transfuzer_entity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: orbital_transfuzer_entity.this.progress = value; break;
                    case 1: orbital_transfuzer_entity.this.maxProgress = value; break;
                    case 2: orbital_transfuzer_entity.this.fuelTime = value; break;
                    case 3: orbital_transfuzer_entity.this.maxFuelTime = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
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



    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    @Override
    public Text getDisplayName() {
        return new LiteralText("Originite Blaster");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new OriginiteBlasterScreenHandler(syncId, inv, this, this.propertyDelegate);
    }



    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("blaster.progress", progress);
        nbt.putInt("blaster.fuelTime", fuelTime);
        nbt.putInt("blaster.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        progress = nbt.getInt("blaster.progress");
        fuelTime = nbt.getInt("blaster.fuelTime");
        maxFuelTime = nbt.getInt("blaster.maxFuelTime");
    }

    private void consumeFuel() {
        if(!getStack(0).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, orbital_transfuzer_entity entity) {
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
        }

        if(hasRecipe(entity)) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem(entity);
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(orbital_transfuzer_entity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(orbital_transfuzer_entity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(orbital_transfuzer_entity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<OriginiteBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(OriginiteBlasterRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(orbital_transfuzer_entity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<OriginiteBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(OriginiteBlasterRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            entity.removeStack(1,1);
            entity.removeStack(2,1);

            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }
}

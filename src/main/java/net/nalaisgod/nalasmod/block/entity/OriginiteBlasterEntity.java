package net.nalaisgod.nalasmod.block.entity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
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
import net.nalaisgod.nalasmod.screen.OriginiteBlasterScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class OriginiteBlasterEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(4, ItemStack.EMPTY);



    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 1200;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public OriginiteBlasterEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORIGINITE_BLASTER, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return OriginiteBlasterEntity.this.progress;
                    case 1: return OriginiteBlasterEntity.this.maxProgress;
                    case 2: return OriginiteBlasterEntity.this.fuelTime;
                    case 3: return OriginiteBlasterEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: OriginiteBlasterEntity.this.progress = value; break;
                    case 1: OriginiteBlasterEntity.this.maxProgress = value; break;
                    case 2: OriginiteBlasterEntity.this.fuelTime = value; break;
                    case 3: OriginiteBlasterEntity.this.maxFuelTime = value; break;
                }
            }

            public int size() {
                return 4;
            }
        };
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

    public static void tick(World world, BlockPos pos, BlockState state, OriginiteBlasterEntity entity) {
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

    private static boolean hasFuelInFuelSlot(OriginiteBlasterEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    private static boolean isConsumingFuel(OriginiteBlasterEntity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean hasRecipe(OriginiteBlasterEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<OriginiteBlasterRecipe> match = world.getRecipeManager()
                .getFirstMatch(OriginiteBlasterRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoResultSlot(inventory)
                && canInsertItemIntoResultSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(OriginiteBlasterEntity entity) {
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

    private static boolean canInsertItemIntoResultSlot(SimpleInventory inventory, ItemStack result) {
        return inventory.getStack(3).getItem() == result.getItem() || inventory.getStack(3).isEmpty();
    }

    private static boolean canInsertAmountIntoResultSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }
}

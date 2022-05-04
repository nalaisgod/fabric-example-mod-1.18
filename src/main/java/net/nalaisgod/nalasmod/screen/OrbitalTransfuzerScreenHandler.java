package net.nalaisgod.nalasmod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.nalaisgod.nalasmod.screen.slot.ModResultSlot;

public class OrbitalTransfuzerScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final World world;

    public OrbitalTransfuzerScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(11));
    }

    public OrbitalTransfuzerScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.ORBITAL_TRANSFUZER_SCREEN_HANDLER, syncId);
        checkSize(inventory, 11);
        this.inventory = inventory;
        this.world = playerInventory.player.world;
        inventory.onOpen(playerInventory.player);

        // Our Slots
        this.addSlot(new Slot(inventory, 0, 79, 4));
        this.addSlot(new Slot(inventory, 1, 117, 20));
        this.addSlot(new Slot(inventory, 2, 127, 57));
        this.addSlot(new Slot(inventory, 3, 117, 96));
        this.addSlot(new Slot(inventory, 4, 79, 112));
        this.addSlot(new Slot(inventory, 5, 41, 96));
        this.addSlot(new Slot(inventory, 6, 25, 58));
        this.addSlot(new Slot(inventory, 7, 41, 20));
        this.addSlot(new Slot(inventory, 8, 152, 108));
        this.addSlot(new Slot(inventory, 9, 8, 108));

        this.addSlot(new ModResultSlot(inventory, 10, 79, 58));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 130 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 188));
        }
    }
}

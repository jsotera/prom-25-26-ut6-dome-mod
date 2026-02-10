package com.jjgames.mods.domemod.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import com.jjgames.mods.domemod.blockentity.DomeConfigChestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public class DomeConfigChestMenu extends AbstractContainerMenu {

    private DomeConfigChestBlockEntity blockEntity;

    public DomeConfigChestMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        super(ModMenuTypes.DOME_CONFIG_CHEST.get(), windowId);

        BlockPos pos = data.readBlockPos();
        BlockEntity be = playerInventory.player.level.getBlockEntity(pos);

        if (be instanceof DomeConfigChestBlockEntity domeBE) {
            this.blockEntity = domeBE;
        } else {
            throw new IllegalStateException("BlockEntity incorrecta");
        }

        int startX = 8;
        int startY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, startX + col * 18, startY + row * 18));
            }
        }
        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInventory, col, startX + col * 18, startY + 58));
        }
    }

    public DomeConfigChestMenu(int windowId, Inventory playerInventory, DomeConfigChestBlockEntity blockEntity) {
        super(ModMenuTypes.DOME_CONFIG_CHEST.get(), windowId);
        this.blockEntity = blockEntity;

        // Inventario del jugador
        int startX = 8;
        int startY = 84;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, startX + col * 18, startY + row * 18));
            }
        }
        for (int col = 0; col < 9; ++col) {
            addSlot(new Slot(playerInventory, col, startX + col * 18, startY + 58));
        }
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level.isClientSide) {
            player.getInventory().setChanged();
            broadcastChanges();
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}
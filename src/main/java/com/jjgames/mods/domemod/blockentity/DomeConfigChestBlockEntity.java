package com.jjgames.mods.domemod.blockentity;

import com.jjgames.mods.domemod.registry.ModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

import com.jjgames.mods.domemod.gui.DomeConfigChestMenu;

public class DomeConfigChestBlockEntity extends BlockEntity implements MenuProvider {

    public DomeConfigChestBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DOME_CONFIG_CHEST.get(), pos, state); // registrado en ModBlockEntities
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Dome Config Chest");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new DomeConfigChestMenu(id, playerInventory, this);
    }
}
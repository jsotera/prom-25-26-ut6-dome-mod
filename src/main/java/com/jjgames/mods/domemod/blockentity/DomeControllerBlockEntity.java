package com.jjgames.mods.domemod.blockentity;

import com.jjgames.mods.domemod.registry.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DomeControllerBlockEntity extends BlockEntity {

    public DomeControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DOME_CONTROLLER.get(), pos, state);
    }

}

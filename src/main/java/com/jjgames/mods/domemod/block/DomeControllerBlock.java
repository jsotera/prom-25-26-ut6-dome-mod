package com.jjgames.mods.domemod.block;

import com.jjgames.mods.domemod.blockentity.DomeControllerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class DomeControllerBlock extends Block implements EntityBlock {

    public DomeControllerBlock() {
        super(Properties
                .of(Material.METAL)
                .strength(-1.0F, 3600000.0F)
                .noOcclusion()
        );
    }

    @Nullable
    @Override
    public DomeControllerBlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DomeControllerBlockEntity(pos, state);
    }

}

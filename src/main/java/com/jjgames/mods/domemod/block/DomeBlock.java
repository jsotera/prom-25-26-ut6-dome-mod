package com.jjgames.mods.domemod.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class DomeBlock extends Block {

    public DomeBlock() {
        super(BlockBehaviour.Properties
                .of(Material.GLASS)
                .strength(-1.0F, 3600000.0F) // irrompible
                .sound(SoundType.GLASS)
                .noOcclusion()
                .isValidSpawn((state, level, pos, type) -> false) // no mobs
        );
    }
}

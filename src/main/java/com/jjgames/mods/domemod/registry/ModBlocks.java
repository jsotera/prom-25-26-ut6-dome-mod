package com.jjgames.mods.domemod.registry;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.block.DomeBlock;
import com.jjgames.mods.domemod.block.DomeConfigChestBlock;
import com.jjgames.mods.domemod.block.DomeControllerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.jjgames.mods.domemod.registry.ModItems.ITEMS;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, DomeMod.MOD_ID);

    public static final RegistryObject<Block> DOME_BLOCK =
            BLOCKS.register("dome_block", DomeBlock::new);

    public static final RegistryObject<Block> DOME_CONTROLLER_BLOCK =
            BLOCKS.register("dome_controller", DomeControllerBlock::new);

    public static final RegistryObject<Block> DOME_CONFIG_CHEST_BLOCK = BLOCKS.register("dome_config_chest", DomeConfigChestBlock::new);

}
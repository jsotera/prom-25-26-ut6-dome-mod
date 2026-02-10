package com.jjgames.mods.domemod.registry;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.blockentity.DomeConfigChestBlockEntity;
import com.jjgames.mods.domemod.blockentity.DomeControllerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DomeMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DomeControllerBlockEntity>> DOME_CONTROLLER =
            BLOCK_ENTITIES.register("dome_controller",
                    () -> BlockEntityType.Builder.of(
                            DomeControllerBlockEntity::new,
                            ModBlocks.DOME_CONTROLLER_BLOCK.get()
                    ).build(null));

    public static final RegistryObject<BlockEntityType<DomeConfigChestBlockEntity>> DOME_CONFIG_CHEST =
            BLOCK_ENTITIES.register("dome_config_chest",
                    () -> BlockEntityType.Builder.of(DomeConfigChestBlockEntity::new,
                                    ModBlocks.DOME_CONFIG_CHEST_BLOCK.get())
                            .build(null)
            );

}


package com.jjgames.mods.domemod;

import com.jjgames.mods.domemod.gui.ModMenuTypes;
import com.jjgames.mods.domemod.network.ModNetworking;
import com.jjgames.mods.domemod.registry.ModBlockEntities;
import com.jjgames.mods.domemod.registry.ModBlocks;
import com.jjgames.mods.domemod.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(DomeMod.MOD_ID)
public class DomeMod {

    public static final String MOD_ID = "domemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DomeMod() {

        LOGGER.info("DomeMod cargado correctamente");

        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlockEntities.BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModMenuTypes.MENUS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModNetworking.register();
    }
}
package com.jjgames.mods.domemod.client;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.gui.DomeConfigChestScreen;
import com.jjgames.mods.domemod.gui.ModMenuTypes;
import com.jjgames.mods.domemod.registry.ModBlockEntities;
import com.jjgames.mods.domemod.registry.ModBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = DomeMod.MOD_ID)
public class ClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        ItemBlockRenderTypes.setRenderLayer(
                ModBlocks.DOME_BLOCK.get(),
                RenderType.translucent()
        );

        event.enqueueWork(() -> {
            MenuScreens.register(
                    ModMenuTypes.DOME_CONFIG_CHEST.get(),
                    DomeConfigChestScreen::new
            );
        });
/*
        BlockEntityRenderers.register(
                ModBlockEntities.DOME_CONFIG_CHEST.get(),
                DomeConfigChestBlockRenderer::new
        );*/
    }
}
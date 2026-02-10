package com.jjgames.mods.domemod.gui;

import com.jjgames.mods.domemod.DomeMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, DomeMod.MOD_ID);

    public static final RegistryObject<MenuType<DomeConfigChestMenu>> DOME_CONFIG_CHEST =
            MENUS.register("dome_config_chest",
                    () -> IForgeMenuType.create((windowId, inv, data) -> {
                        // ESTE constructor DEBE existir
                        return new DomeConfigChestMenu(windowId, inv, data);
                    })
            );
}
package com.jjgames.mods.domemod.registry;

import com.jjgames.mods.domemod.DomeMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DomeMod.MOD_ID);

    public static final RegistryObject<Item> DOME_BLOCK_ITEM =
            ITEMS.register("dome_block",
                    () -> new BlockItem(ModBlocks.DOME_BLOCK.get(),
                            new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> DOME_CONFIG_CHEST_ITEM =
            ITEMS.register("dome_config_chest",
                    () -> new BlockItem(
                            ModBlocks.DOME_CONFIG_CHEST_BLOCK.get(),
                            new Item.Properties()
                    ));
}

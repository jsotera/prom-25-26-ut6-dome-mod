package com.jjgames.mods.domemod.events;

import com.jjgames.mods.domemod.blockentity.DomeControllerBlockEntity;
import com.jjgames.mods.domemod.data.DomeWorldData;
import com.jjgames.mods.domemod.util.DomeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BlockPlaceEvents {

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        ServerLevel level = player.getLevel();
        DomeWorldData domeData = DomeWorldData.get(level);

        if (!domeData.isDomeInitialized()) {
            return;
        }

        if(!DomeUtil.estaDentro(level, event.getPos())){
            event.setCanceled(true);
        }
    }
}
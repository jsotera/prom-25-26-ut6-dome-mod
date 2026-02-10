package com.jjgames.mods.domemod.events;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.data.DomeWorldData;

import com.jjgames.mods.domemod.registry.ModBlocks;
import com.jjgames.mods.domemod.util.DomeUtil;
import com.jjgames.mods.domemod.util.GameUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DomeMod.MOD_ID)
public class BlockBreakEvents {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {

        // Seguridad extra: solo servidor
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }

        if (!(event.getPlayer() instanceof ServerPlayer player)) {
            return;
        }

        // Posición del bloque roto
        BlockPos pos = event.getPos();
        DomeWorldData domeData = DomeWorldData.get(serverLevel);

        if (!domeData.isDomeInitialized()) {
            DomeMod.LOGGER.info("Primer bloque roto en {} {} {}. Aquí se iniciará la cúpula.",pos.getX(), pos.getY(), pos.getZ());
            domeData.setDomeInitialized(true);
            // esto llama al metodo initialize del DomeControllerBlockEntity
            //serverLevel.setBlockAndUpdate(pos,ModBlocks.DOME_CONTROLLER_BLOCK.get().defaultBlockState());
            domeData.setControllerPos(pos);
            GameUtil.colocarBloque(serverLevel, pos, ModBlocks.DOME_CONFIG_CHEST_BLOCK.get());
            DomeUtil.generarCupulaInicial(serverLevel, pos);
            event.setCanceled(true);
            return;
        }

        if(!DomeUtil.estaDentro(serverLevel, event.getPos())){
            event.setCanceled(true);
        }

    }
}
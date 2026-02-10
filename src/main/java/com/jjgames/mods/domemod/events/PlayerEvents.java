package com.jjgames.mods.domemod.events;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.data.DomeWorldData;
import com.jjgames.mods.domemod.network.ModNetworking;
import com.jjgames.mods.domemod.network.packets.SyncMoneyPacket;
import com.jjgames.mods.domemod.util.DomeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber
public class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.getEntity() instanceof ServerPlayer player) {

            // Leemos el dinero que tiene guardado en su archivo NBT
            DomeWorldData domeData = DomeWorldData.get(player.getLevel());

            // Enviamos el paquete inmediatamente al cliente que acaba de entrar
            ModNetworking.MONEY_CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new SyncMoneyPacket(domeData.getDomeTotalMoney()));

            // Opcional: Un mensaje de bienvenida en la consola para debug
            System.out.println("Sincronizando dinero para " + player.getName().getString() + ": " + domeData.getDomeTotalMoney());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        var level = player.getLevel();
        DomeWorldData domeData = DomeWorldData.get(level);
        BlockPos bloqueDeControl = domeData.getControllerPos();

        if (!domeData.isDomeInitialized()) return;
        if (domeData.getControllerPos() == null) return;

        Vec3 teleportPos = new Vec3(bloqueDeControl.getX() + 0.5,
                bloqueDeControl.getY() + 0.5,
                bloqueDeControl.getZ() + 0.5);

        if (!DomeUtil.estaDentro(level, player.blockPosition())) {
            player.teleportTo(teleportPos.x, teleportPos.y, teleportPos.z);
        }


        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer sp) {
            // Un mensaje por segundo
            if (sp.tickCount % 20 == 0) {
                if(!sp.getPersistentData().contains("domemod_money")){
                    System.out.println("AL PRINCIPIO NUNCA EXISTE");
                    sp.getPersistentData().putInt("domemod_money", 0);
                }
                int money = sp.getPersistentData().getInt("domemod_money");
                if(money != domeData.getDomeTotalMoney()){
                    sp.getPersistentData().putInt("domemod_money", domeData.getDomeTotalMoney());
                    // Si el dinero es diferente, lo enviamos al cliente. No de manera constante, que sino petamos el server
                    System.out.println("Enviando pasta:  "+domeData.getDomeTotalMoney());
                    ModNetworking.MONEY_CHANNEL.send(PacketDistributor.PLAYER.with(() -> sp), new SyncMoneyPacket(domeData.getDomeTotalMoney()));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        // Verificamos que estamos en el servidor
        if (event.getEntity() instanceof ServerPlayer player) {

            // Leemos el dinero que tiene guardado en su archivo NBT
            DomeWorldData domeData = DomeWorldData.get(player.getLevel());

            // Enviamos el paquete inmediatamente al cliente que acaba de entrar
            ModNetworking.MONEY_CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new SyncMoneyPacket(domeData.getDomeTotalMoney()));

            // Opcional: Un mensaje de bienvenida en la consola para debug
            System.out.println("Sincronizando dinero para " + player.getName().getString() + ": " + domeData.getDomeTotalMoney());
        }
    }
}

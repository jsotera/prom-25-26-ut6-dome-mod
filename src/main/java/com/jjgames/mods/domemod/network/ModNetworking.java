package com.jjgames.mods.domemod.network;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.network.packets.DomeConfigActionPacket;
import com.jjgames.mods.domemod.network.packets.SyncMoneyPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {

    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(DomeMod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static final SimpleChannel MONEY_CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("tu_mod_id", "main"),
            () -> "1", s -> true, s -> true
    );

    private static int packetId = 0;

    public static void register() {
        // aquí se registran los paquetes
        CHANNEL.registerMessage(
                nextId(),
                DomeConfigActionPacket.class,
                DomeConfigActionPacket::encode,
                DomeConfigActionPacket::decode,
                DomeConfigActionPacket::handle
        );
        MONEY_CHANNEL.registerMessage(0, SyncMoneyPacket.class, SyncMoneyPacket::encode, SyncMoneyPacket::decode, SyncMoneyPacket::handle);
    }

    public static int nextId() {
        return packetId++;
    }
}
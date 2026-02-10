package com.jjgames.mods.domemod.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SyncMoneyPacket(int money) {
    public static void encode(SyncMoneyPacket msg, FriendlyByteBuf buffer) {
        buffer.writeInt(msg.money);
    }

    public static SyncMoneyPacket decode(FriendlyByteBuf buffer) {
        return new SyncMoneyPacket(buffer.readInt());
    }

    public static void handle(SyncMoneyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Esto se ejecuta en tu PC (Cliente)
            var player = net.minecraft.client.Minecraft.getInstance().player;
            if (player != null) {
                player.getPersistentData().putInt("domemod_money", msg.money);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
package com.jjgames.mods.domemod.network.packets;

import com.jjgames.mods.domemod.util.DomeUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DomeConfigActionPacket {

    private final int actionId;

    public DomeConfigActionPacket(int actionId) {
        this.actionId = actionId;
    }

    public static void encode(DomeConfigActionPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.actionId);
    }

    public static DomeConfigActionPacket decode(FriendlyByteBuf buf) {
        return new DomeConfigActionPacket(buf.readInt());
    }

    // invocar al servidor desde aqui
    public static void handle(DomeConfigActionPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            ServerLevel level = player.getLevel();
            int action = msg.actionId;
            DomeUtil.realizarAccion(player, action);
        });

        ctx.get().setPacketHandled(true);
    }
}
package com.jjgames.mods.domemod.client.hud;

import com.jjgames.mods.domemod.DomeMod;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.client.gui.GuiComponent.fill;

@Mod.EventBusSubscriber(
        modid = DomeMod.MOD_ID,
        value = Dist.CLIENT
)
public class MoneyHUD {

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        PoseStack poseStack = event.getPoseStack();

        // 👉 AQUÍ sacas el dinero del jugador
        int money = mc.player.getPersistentData().getInt("domemod_money");

        String text = "Dinero: " + money + " $";

        // Posición en pantalla
        int x = mc.getWindow().getGuiScaledWidth() - mc.font.width(text) - 10;
        int y = 10;

        fill(poseStack, x - 4, y - 4, x + mc.font.width(text) + 4, y + 12, 0x80000000);

        mc.font.drawShadow(poseStack, text, x, y, 0xFFD700);
    }
}
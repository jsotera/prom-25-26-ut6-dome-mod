package com.jjgames.mods.domemod.gui;

import com.jjgames.mods.domemod.data.DomeWorldData;
import com.jjgames.mods.domemod.model.DomeChestSlot;
import com.jjgames.mods.domemod.network.ModNetworking;
import com.jjgames.mods.domemod.network.packets.DomeConfigActionPacket;
import com.jjgames.mods.domemod.util.DomeUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.jjgames.mods.domemod.DomeMod;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DomeConfigChestScreen extends AbstractContainerScreen<DomeConfigChestMenu> {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(DomeMod.MOD_ID, "textures/gui/dome_config_chest.png");
    private static final ResourceLocation ICONS = new ResourceLocation(DomeMod.MOD_ID, "textures/gui/options.png");

    private static final int ICON_SIZE = 32;
    private static final int ICONS_TEXTURE_WIDTH = 1024;
    private static final int ICONS_TEXTURE_HEIGHT = 3648;

    private int currentPage = 0;

    public DomeConfigChestScreen(DomeConfigChestMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        // los ultimos dos es para decir cuanto ocupa, ya que sino lo intenta poner en 256×256
        blit(poseStack, x, y, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        RenderSystem.setShaderTexture(0, ICONS);

        int guiX = (width - imageWidth) / 2;
        int guiY = (height - imageHeight) / 2;

        DomeUtil.generarOpcionesCofre();

        // TODO 200: Aqui podemos personalizar las opciones con algun fondo adicional, procesar un hashmap o lo que se quiera
        List<DomeChestSlot> opciones = DomeUtil.opciones;
        for (DomeChestSlot opcion : opciones) {
            if(opcion.getNumPagina() == currentPage){
                if(currentPage==1){
                    drawIcon(poseStack, guiX, guiY, guiX+36*opcion.getColumnaCofre()+16, guiY+36*opcion.getFilaCofre()+36, 0, 10);
                } else if (currentPage==2){
                    drawIcon(poseStack, guiX, guiY, guiX+36*opcion.getColumnaCofre()+16, guiY+36*opcion.getFilaCofre()+36, 0, 15);
                }
                Minecraft.getInstance().getItemRenderer().renderAndDecorateFakeItem(
                        opcion.getItemStack(),guiX+18*opcion.getColumnaCofre()+8,guiY+18*opcion.getFilaCofre()+18
                );
            }
        }

    }

    private void drawIcon(PoseStack poseStack, int guiX, int guiY, int screenX, int screenY, int row, int col) {
        int u = col * ICON_SIZE;
        int v = row * ICON_SIZE;
        RenderSystem.setShaderTexture(0, ICONS);
        poseStack.pushPose();
        poseStack.scale(0.5f, 0.5f, 1f);
        blit(poseStack, guiX + screenX +1, guiY + screenY+1, ICON_SIZE-3, ICON_SIZE-3, u, v, ICON_SIZE, ICON_SIZE, ICONS_TEXTURE_WIDTH, ICONS_TEXTURE_HEIGHT);
        poseStack.popPose();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);

        List<DomeChestSlot> opciones = DomeUtil.opciones;
        for (DomeChestSlot opcion : opciones) {
            if (opcion.getNumPagina() == currentPage && isHovering(18*opcion.getColumnaCofre()+8, 18*opcion.getFilaCofre()+18, 16, 16, mouseX, mouseY)) {
                drawHoverOverlay(poseStack, 18*opcion.getColumnaCofre()+8, 18*opcion.getFilaCofre()+18, 16, 16);

                List<Component> lineas = new ArrayList<>();
                Map<String, List<ChatFormatting>> msgMap = opcion.getTooltopMsgs();
                for (Map.Entry<String, List<ChatFormatting>> entry : msgMap.entrySet()) {
                    MutableComponent texto = Component.literal(entry.getKey());
                    for (ChatFormatting formato : entry.getValue()) {
                        texto.withStyle(formato);
                    }
                    lineas.add(texto);
                }

                renderTooltip(poseStack, lineas, Optional.empty(), mouseX, mouseY);
            }
        }
    }

    public boolean isHovering(int slotX, int slotY, int width, int height, double mouseX, double mouseY) {
        int x = (this.width - imageWidth) / 2 + slotX;
        int y = (this.height - imageHeight) / 2 + slotY;
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private void drawHoverOverlay(PoseStack poseStack,int x,int y,int width,int height) {
        int guiLeft = (this.width - this.imageWidth) / 2;
        int guiTop  = (this.height - this.imageHeight) / 2;
        RenderSystem.disableDepthTest();
        fill(poseStack,guiLeft + x,guiTop + y,guiLeft + x + width,guiTop + y + height,0x80FFFFFF);
        RenderSystem.enableDepthTest();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Por si se quiere en funcion del tipo de clic, realizar una accion u otra
        //button == 0 --> CLICK IZQUIERDO
        //button == 1 --> CLICK DERECHO
        //boolean shift = Screen.hasShiftDown();
        //boolean ctrl  = Screen.hasControlDown();
        //boolean alt   = Screen.hasAltDown();

        List<DomeChestSlot> opciones = DomeUtil.opciones;
        for (DomeChestSlot opcion : opciones) {
            if (opcion.getNumPagina() == currentPage && isHovering(18*opcion.getColumnaCofre()+8, 18*opcion.getFilaCofre()+18, 16, 16, mouseX, mouseY)) {
                if(opcion.getAccion1() < 1){
                    currentPage = Math.abs(opcion.getAccion1());
                    System.out.println(currentPage);
                    break;
                } else {
                    ModNetworking.CHANNEL.sendToServer(new DomeConfigActionPacket(opcion.getAccion1()));
                    break;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}
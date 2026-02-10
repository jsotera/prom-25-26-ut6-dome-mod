package com.jjgames.mods.domemod.model;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class DomeChestSlot {

    private int filaCofre, columnaCofre;
    private int numPagina;

    private ItemStack itemStack;

    private Map<String, List<ChatFormatting>> tooltopMsgs;

    private int accion1;

    public DomeChestSlot(int filaCofre, int columnaCofre, int numPagina, ItemStack itemStack, String tooltipMsg, int accion1) {
        this.filaCofre = filaCofre;
        this.columnaCofre = columnaCofre;
        this.numPagina = numPagina;
        this.itemStack = itemStack;
        this.accion1 = accion1;
        tooltopMsgs = new LinkedHashMap<>();
        List<ChatFormatting> formatos = new ArrayList<>();
        formatos.add(ChatFormatting.RESET);
        tooltopMsgs.put(tooltipMsg, formatos);
    }

    public int getFilaCofre() {
        return filaCofre;
    }

    public void setFilaCofre(int filaCofre) {
        this.filaCofre = filaCofre;
    }

    public int getColumnaCofre() {
        return columnaCofre;
    }

    public void setColumnaCofre(int columnaCofre) {
        this.columnaCofre = columnaCofre;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getAccion1() {
        return accion1;
    }

    public void setAccion1(int accion1) {
        this.accion1 = accion1;
    }

    public int getNumPagina() {
        return numPagina;
    }

    public void setNumPagina(int numPagina) {
        this.numPagina = numPagina;
    }

    public Map<String, List<ChatFormatting>> getTooltopMsgs() {
        return tooltopMsgs;
    }

    public void setTooltopMsgs(Map<String, List<ChatFormatting>> tooltopMsgs) {
        this.tooltopMsgs = tooltopMsgs;
    }
}
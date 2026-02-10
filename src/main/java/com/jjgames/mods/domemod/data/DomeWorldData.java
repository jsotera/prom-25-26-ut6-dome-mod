package com.jjgames.mods.domemod.data;

import com.jjgames.mods.domemod.model.Direccion;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;

public class DomeWorldData extends SavedData {

    private static final String DATA_NAME = "domemod_dome_data";

    public static final int INITIAL_DOME_RADIUS = 5;

    private boolean domeInitialized = false;
    private BlockPos controllerPos;
    private int domeTotalMoney = 1000;

    private int distNorte = INITIAL_DOME_RADIUS;
    private int distSur = INITIAL_DOME_RADIUS;
    private int distEste = INITIAL_DOME_RADIUS;
    private int distOeste = INITIAL_DOME_RADIUS;
    private int distArriba = INITIAL_DOME_RADIUS;
    private int distAbajo = INITIAL_DOME_RADIUS;

    public DomeWorldData() {
    }

    public static DomeWorldData load(CompoundTag tag) {
        DomeWorldData data = new DomeWorldData();
        data.domeInitialized = tag.getBoolean("domeInitialized");

        if (tag.contains("controllerPos")) {
            data.controllerPos = BlockPos.of(tag.getLong("controllerPos"));
        }

        data.distNorte = tag.getInt("distNorte");
        data.distSur = tag.getInt("distSur");
        data.distEste = tag.getInt("distEste");
        data.distOeste = tag.getInt("distOeste");
        data.distArriba = tag.getInt("distArriba");
        data.distAbajo = tag.getInt("distAbajo");

        if (tag.contains("domeTotalMoney")) {
            data.domeTotalMoney = tag.getInt("domeTotalMoney");
        } else {
            data.domeTotalMoney = 1000;
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        if (controllerPos != null) {
            tag.putLong("controllerPos", controllerPos.asLong());
        }
        tag.putBoolean("domeInitialized", domeInitialized);

        tag.putInt("distNorte",  distNorte);
        tag.putInt("distSur",  distSur);
        tag.putInt("distEste",  distEste);
        tag.putInt("distOeste",  distOeste);
        tag.putInt("distArriba",  distArriba);
        tag.putInt("distAbajo",  distAbajo);
        tag.putInt("domeTotalMoney",  domeTotalMoney);

        return tag;
    }

    public boolean isDomeInitialized() {
        return domeInitialized;
    }

    public void setDomeInitialized(boolean value) {
        this.domeInitialized = value;
        this.setDirty();
    }

    public BlockPos getControllerPos() {
        return controllerPos;
    }

    public void setControllerPos(BlockPos pos) {
        this.controllerPos = pos;
        setDirty();
    }

    public int getDistNorte() {
        return distNorte;
    }

    public void setDistNorte(int distNorte) {
        this.distNorte = distNorte;
        setDirty();
    }

    public int getDistSur() {
        return distSur;
    }

    public void setDistSur(int distSur) {
        this.distSur = distSur;
        setDirty();
    }

    public int getDistEste() {
        return distEste;
    }

    public void setDistEste(int distEste) {
        this.distEste = distEste;
        setDirty();
    }

    public int getDistOeste() {
        return distOeste;
    }

    public void setDistOeste(int distOeste) {
        this.distOeste = distOeste;
        setDirty();
    }

    public int getDistArriba() {
        return distArriba;
    }

    public void setDistArriba(int distArriba) {
        this.distArriba = distArriba;
        setDirty();
    }

    public int getDistAbajo() {
        return distAbajo;
    }

    public void setDistAbajo(int distAbajo) {
        this.distAbajo = distAbajo;
        setDirty();
    }

    public int getDomeTotalMoney() {
        return domeTotalMoney;
    }

    public void setDomeTotalMoney(int domeTotalMoney) {
        this.domeTotalMoney = domeTotalMoney;
        setDirty();
    }

    public static DomeWorldData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                DomeWorldData::load,   // cómo cargar
                DomeWorldData::new,    // cómo crear si no existe
                DATA_NAME              // nombre del archivo
        );
    }
}
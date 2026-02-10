package com.jjgames.mods.domemod.util;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.data.DomeWorldData;
import com.jjgames.mods.domemod.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GameUtil {

    public static void colocarBloque(ServerLevel level, BlockPos pos, Block block) {
        if (level == null || pos == null || block == null) return;
        BlockState state = block.defaultBlockState();
        // Flags:
        // 3 = actualizar cliente + vecinos
        level.setBlock(pos, state, 3);
    }

    public static BlockPos generarBlockPos(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }

    public static void intercambiarBloques(ServerLevel level, BlockPos pos1, BlockPos pos2) {

        //DomeMod.LOGGER.info("Intercambiando bloque en {} {} {} con el bloque de {} {} {}",pos1.getX(), pos1.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
        if (pos1.equals(pos2)) return;

        BlockState state1 = level.getBlockState(pos1);
        BlockState state2 = level.getBlockState(pos2);

        // Guardar BlockEntities (si existen)
        BlockEntity be1 = level.getBlockEntity(pos1);
        BlockEntity be2 = level.getBlockEntity(pos2);

        CompoundTag tag1 = null;
        CompoundTag tag2 = null;

        if (be1 != null) {
            tag1 = be1.saveWithFullMetadata();
            level.removeBlockEntity(pos1);
        }

        if (be2 != null) {
            tag2 = be2.saveWithFullMetadata();
            level.removeBlockEntity(pos2);
        }

        // Intercambiar BlockStates
        level.setBlock(pos1, state2, 3);
        level.setBlock(pos2, state1, 3);

        // Restaurar BlockEntities
        if (tag2 != null) {
            BlockEntity newBe1 = level.getBlockEntity(pos1);
            if (newBe1 != null) {
                newBe1.load(tag2);
                newBe1.setChanged();
            }
        }

        if (tag1 != null) {
            BlockEntity newBe2 = level.getBlockEntity(pos2);
            if (newBe2 != null) {
                newBe2.load(tag1);
                newBe2.setChanged();
            }
        }

        // Forzar actualización visual
        level.sendBlockUpdated(pos1, state1, state2, 3);
        level.sendBlockUpdated(pos2, state2, state1, 3);
    }

    public static void darItem(ServerPlayer jugador, Item item, int cantidad) {

        ItemStack stack = new ItemStack(item, cantidad);

        // Intenta meterlo en el inventario
        boolean added = jugador.getInventory().add(stack);

        // Si el inventario está lleno, lo tira al suelo
        if (!added) {
            System.out.println("El inventario esta lleno");
            //player.drop(stack, false);
        }
    }

    public static void quitarItem(ServerPlayer player, Item item, int amount) {

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);

            if (!stack.isEmpty() && stack.getItem() == item) {
                int removed = Math.min(amount, stack.getCount());
                stack.shrink(removed);
                amount -= removed;

                if (amount <= 0) {
                    break;
                }
            }
        }
    }
}

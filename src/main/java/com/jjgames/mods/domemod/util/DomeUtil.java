package com.jjgames.mods.domemod.util;

import com.jjgames.mods.domemod.DomeMod;
import com.jjgames.mods.domemod.data.DomeWorldData;
import com.jjgames.mods.domemod.model.DomeChestSlot;
import com.jjgames.mods.domemod.registry.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class DomeUtil {

    public static List<DomeChestSlot> opciones;

    public static Map<Integer, Map.Entry<Item, Integer>> compraVenta;

    public static void generarOpcionesCofre(){
        if(compraVenta ==null){
            generarMapaCompraVenta();
        }
        if(opciones==null){
            generarItemsCofre();
        }
    }

    private static void generarMapaCompraVenta() {
        // TODO 100: Definir las operaciones de compra venta, es un ejemplo

        compraVenta = new HashMap<>();

        compraVenta.put(2001, Map.entry(Blocks.DIAMOND_BLOCK.asItem(), 100));
        compraVenta.put(2002, Map.entry(Items.ARROW.asItem(), 20));
        // Materiales Básicos y Minerales
        compraVenta.put(2003, Map.entry(Items.IRON_INGOT, 15));
        compraVenta.put(2004, Map.entry(Items.GOLD_INGOT, 30));
        compraVenta.put(2005, Map.entry(Items.COPPER_INGOT, 10));
        compraVenta.put(2006, Map.entry(Items.NETHERITE_INGOT, 500));
        compraVenta.put(2007, Map.entry(Items.COAL, 5));
        compraVenta.put(2008, Map.entry(Items.LAPIS_LAZULI, 12));
        compraVenta.put(2009, Map.entry(Items.EMERALD, 50));
        compraVenta.put(2010, Map.entry(Items.QUARTZ, 8));
        // Bloques de Construcción
        compraVenta.put(2011, Map.entry(Blocks.STONE.asItem(), 2));
        compraVenta.put(2012, Map.entry(Blocks.COBBLESTONE.asItem(), 1));
        compraVenta.put(2013, Map.entry(Blocks.OAK_LOG.asItem(), 4));
        compraVenta.put(2014, Map.entry(Blocks.SPRUCE_LOG.asItem(), 4));
        compraVenta.put(2015, Map.entry(Blocks.DIRT.asItem(), 1));
        compraVenta.put(2016, Map.entry(Blocks.SAND.asItem(), 2));
        compraVenta.put(2017, Map.entry(Blocks.GRAVEL.asItem(), 2));
        compraVenta.put(2018, Map.entry(Blocks.GLASS.asItem(), 5));
        compraVenta.put(2019, Map.entry(Blocks.OBSIDIAN.asItem(), 40));
        compraVenta.put(2020, Map.entry(Blocks.MOSS_BLOCK.asItem(), 10));

// Comida y Agricultura
        compraVenta.put(2021, Map.entry(Items.WHEAT, 3));
        compraVenta.put(2022, Map.entry(Items.BREAD, 6));
        compraVenta.put(2023, Map.entry(Items.APPLE, 5));
        compraVenta.put(2024, Map.entry(Items.COOKED_BEEF, 12));
        compraVenta.put(2025, Map.entry(Items.COOKED_CHICKEN, 10));
        compraVenta.put(2026, Map.entry(Items.GOLDEN_APPLE, 75));
        compraVenta.put(2027, Map.entry(Items.POTATO, 2));
        compraVenta.put(2028, Map.entry(Items.CARROT, 2));

        compraVenta.put(2029, Map.entry(Items.IRON_SWORD, 40));
        compraVenta.put(2030, Map.entry(Items.DIAMOND_SWORD, 250));
        compraVenta.put(2031, Map.entry(Items.BOW, 30));
        compraVenta.put(2032, Map.entry(Items.SHIELD, 45));
        compraVenta.put(2033, Map.entry(Items.IRON_PICKAXE, 45));
        compraVenta.put(2034, Map.entry(Items.DIAMOND_PICKAXE, 300));

        compraVenta.put(2035, Map.entry(Items.TORCH, 2));
        compraVenta.put(2036, Map.entry(Items.BUCKET, 15));
        compraVenta.put(2037, Map.entry(Items.WATER_BUCKET, 20));
        compraVenta.put(2038, Map.entry(Items.LAVA_BUCKET, 35));
        compraVenta.put(2039, Map.entry(Items.ENDER_PEARL, 60));
        compraVenta.put(2040, Map.entry(Items.BLAZE_ROD, 45));
        compraVenta.put(2041, Map.entry(Items.GUNPOWDER, 15));
        compraVenta.put(2042, Map.entry(Items.STRING, 5));
        compraVenta.put(2043, Map.entry(Items.FEATHER, 3));
        compraVenta.put(2044, Map.entry(Items.LEATHER, 10));
        compraVenta.put(2045, Map.entry(Items.PAPER, 4));
        compraVenta.put(2046, Map.entry(Items.BOOK, 15));
        compraVenta.put(2047, Map.entry(Items.SLIME_BALL, 25));

        compraVenta.put(2048, Map.entry(Items.REDSTONE, 10));
        compraVenta.put(2049, Map.entry(Blocks.PISTON.asItem(), 30));
        compraVenta.put(2050, Map.entry(Blocks.STICKY_PISTON.asItem(), 50));
        compraVenta.put(2051, Map.entry(Blocks.REPEATER.asItem(), 20));
        compraVenta.put(2052, Map.entry(Blocks.COMPARATOR.asItem(), 25));

        Map<Integer, Map.Entry<Item, Integer>> itemsVenta = new HashMap<>();
        Set<Map.Entry<Integer, Map.Entry<Item, Integer>>> entries = compraVenta.entrySet();
        for (Map.Entry<Integer, Map.Entry<Item, Integer>> entry : entries) {
            int accionId = entry.getKey()+1000;
            Map.Entry<Item, Integer> itemEntry = Map.entry(entry.getValue().getKey(), entry.getValue().getValue()/4);
            itemsVenta.put(accionId, itemEntry);
        }

        compraVenta.put(3001, Map.entry(Blocks.DIAMOND_BLOCK.asItem(), 25));
        compraVenta.put(3002, Map.entry(Items.ARROW.asItem(), 5));
    }

    private static void generarItemsCofre() {
        DomeMod.LOGGER.info("GENERANDO ITEMS PARA MI COFRE");
        opciones = new ArrayList<>();
        // TODO 101: Definir las opciones de nuestro cofre
        //  Se pueden emplear las clases Blocks.ALGO e Items.ALGO para obtener la imagen de esos objetos en nuestro slot
        // ACCIONES DE EJEMPLO
        DomeChestSlot slot = new DomeChestSlot(0, 3, 0, new ItemStack(Blocks.REDSTONE_BLOCK), "Colocar Bloque random en posicion random - tranqui, no muy lejos", 101);
        // Ejemplo de personalizacion de mensaje
        Map<String, List<ChatFormatting>> tooltopMsgs = new LinkedHashMap<>();
        List<ChatFormatting> formatos = new ArrayList<>();
        formatos.add(ChatFormatting.RESET);
        tooltopMsgs.put("Colocar Bloque random en posicion random", formatos);
        formatos = new ArrayList<>();
        formatos.add(ChatFormatting.GOLD);
        formatos.add(ChatFormatting.UNDERLINE);
        tooltopMsgs.put("tranqui, no muy lejos", formatos);
        slot.setTooltopMsgs(tooltopMsgs);
        opciones.add(slot);
        opciones.add(new DomeChestSlot(2, 3, 0, new ItemStack(Items.DIAMOND_SWORD), "Obtener espada de diamante - Nunca ha sido tan facil", 1002));
        opciones.add(new DomeChestSlot(1, 4, 0, new ItemStack(Blocks.TNT), "Perder objeto aleatorio", 1003));
        opciones.add(new DomeChestSlot(1, 2, 0, new ItemStack(Blocks.GOLD_BLOCK), "Ganar dinero - ¡Me ha tocado la loteria!", 1004));
        opciones.add(new DomeChestSlot(0, 6, 0, new ItemStack(Blocks.COAL_BLOCK), "Perder dinero - ¿Dónde he perdido la pasta?", 1005));
        // COMPRA BLOQUES - EJEMPLOS
        opciones.add(new DomeChestSlot(0, 1, 1, new ItemStack(Blocks.DIAMOND_BLOCK), "COMPRAR DIAMANTE - 100$", 2001));
        opciones.add(new DomeChestSlot(0, 2, 1, new ItemStack(Items.ARROW), "COMPRAR FLECHA - 25$", 2002));
        // VENDER BLOQUES - EJEMPLOS
        opciones.add(new DomeChestSlot(0, 1, 2, new ItemStack(Blocks.DIAMOND_BLOCK), "VENDER DIAMANTE - 20$",3001));
        opciones.add(new DomeChestSlot(0, 2, 2, new ItemStack(Items.ARROW), "VENDER FLECHA - 5$", 3002));
        // PAGINACION
        opciones.add(new DomeChestSlot(1, 8, 0, new ItemStack(Items.ARROW), "Pag. SIGUIENTE", -1));
        opciones.add(new DomeChestSlot(1, 0, 1, new ItemStack(Items.ARROW), "Pag. ANTERIOR", 0));
        opciones.add(new DomeChestSlot(1, 8, 1, new ItemStack(Items.ARROW), "Pag. SIGUIENTE", -2));
        opciones.add(new DomeChestSlot(1, 0, 2, new ItemStack(Items.ARROW), "Pag. ANTERIOR", -1));

        // Mis opciones
        opciones.add(new DomeChestSlot(0, 0, 0, new ItemStack(Items.GLASS), "Ampliar SUR", 1006));
    }

    public static void realizarAccion(ServerPlayer player, int accionId) {
        // Aqui teneis el objeto level
        ServerLevel level = player.getLevel();
        DomeMod.LOGGER.info("Realizando la accion {}", accionId);
        // TODO 102: En funcion de la accion que nos llega, invocar al metodo correspondiente que querais
        if(accionId<2000){
            // ACCIONES DE CONFIGURACION
            switch (accionId) {
                case 1001:
                    generarBloqueEnPosRandom(level);
                    break;
                case 1002:
                    obtenerEspadaDiamante(player);
                    break;
                case 1003:
                    perderItemRandom(player);
                    break;
                case 1004:
                    modificarDinero(level, 250);
                    break;
                case 1005:
                    modificarDinero(level, -125);
                    break;
                case 1006:
                    ampliarSur(level);
                    break;
            }
        } else if (accionId<3000) {
            // ACCIONES DE COMPRA
            comprar(player, accionId);
        } else if (accionId<4000) {
            // ACCIONES DE VENTA
            vender(player, accionId);
        }
    }

    private static void ampliarSur(ServerLevel level) {
        // NORTE --> Z NEGATIVO --> domeData.getDistNorte()
        // SUR --> Z POSITIVO --> domeData.getDistSur()
        // OESTE --> X NEGATIVO --> domeData.getDistOeste()
        // ESTE --> X POSITIVO --> domeData.getDistEste()
        // ABAJO --> Y NEGATIVO --> domeData.getDistAbajo()
        // ARRIBA --> Y POSITIVO --> domeData.getDistArriba()
        DomeWorldData domeData = DomeWorldData.get(level);
        BlockPos centroCupula = domeData.getControllerPos();

        int z = domeData.getDistSur();
        for (int x = -domeData.getDistOeste(); x <=domeData.getDistEste(); x++) {
            for (int y = -domeData.getDistAbajo(); y <=domeData.getDistArriba(); y++) {

                if(
                        x == -domeData.getDistOeste() ||
                        x==domeData.getDistEste() ||
                        y==-domeData.getDistAbajo() ||
                        y==domeData.getDistArriba()
                ){
                    BlockPos posPared = new BlockPos(centroCupula.getX()+x, centroCupula.getY()+y, centroCupula.getZ()+z+1);
                    GameUtil.colocarBloque(level, posPared, Blocks.GLASS);
                } else {
                    BlockPos from = GameUtil.generarBlockPos(centroCupula.getX()+x, centroCupula.getY()+y, centroCupula.getZ()+z);
                    BlockPos to = GameUtil.generarBlockPos(centroCupula.getX()+x, centroCupula.getY()+y, centroCupula.getZ()+z+1);
                    GameUtil.intercambiarBloques(level, from, to);
                }
            }
        }

        domeData.setDistSur(z+1);

    }

    public static void generarCupulaInicial(ServerLevel level, BlockPos center) {
        // TODO 103: Generar una cupula que rodee al jugador en la distancia que corresponda
        BlockState domeState = ModBlocks.DOME_BLOCK.get().defaultBlockState();
        DomeWorldData domeData = DomeWorldData.get(level);

        // NORTE --> Z NEGATIVO --> domeData.getDistNorte()
        // SUR --> Z POSITIVO --> domeData.getDistSur()
        // OESTE --> X NEGATIVO --> domeData.getDistOeste()
        // ESTE --> X POSITIVO --> domeData.getDistEste()
        // ABAJO --> Y NEGATIVO --> domeData.getDistAbajo()
        // ARRIBA --> Y POSITIVO --> domeData.getDistArriba()

        for (int x = -domeData.getDistOeste(); x <=domeData.getDistEste(); x++) {
            for (int y = -domeData.getDistAbajo(); y <=domeData.getDistArriba(); y++) {
                for (int z = -domeData.getDistNorte(); z <=domeData.getDistSur(); z++) {
                    if(
                            x == -domeData.getDistOeste() ||
                            x==domeData.getDistEste() ||
                            y==-domeData.getDistAbajo() ||
                            y==domeData.getDistArriba() ||
                            z==-domeData.getDistNorte() ||
                            z == domeData.getDistSur()
                    ){
                        BlockPos posPared = new BlockPos(center.getX()+x, center.getY()+y, center.getZ()+z);
                        GameUtil.colocarBloque(level, posPared, Blocks.GLASS);
                    }
                }
            }
        }
    }

    public static boolean estaDentro(ServerLevel level, BlockPos pos) {
        // TODO 104: Analizar si la posicion que nos llega esta dentro de la cupula
        DomeWorldData domeData = DomeWorldData.get(level);
        BlockPos bloqueDeControl = domeData.getControllerPos();
        // NORTE --> Z NEGATIVO --> domeData.getDistNorte()
        // SUR --> Z POSITIVO --> domeData.getDistSur()
        // OESTE --> X NEGATIVO --> domeData.getDistOeste()
        // ESTE --> X POSITIVO --> domeData.getDistEste()
        // ABAJO --> Y NEGATIVO --> domeData.getDistAbajo()
        // ARRIBA --> Y POSITIVO --> domeData.getDistArriba()
        if(bloqueDeControl==null){
            return true;
        }

        if(
                pos.getX() > bloqueDeControl.getX() + domeData.getDistEste() ||
                pos.getX() < bloqueDeControl.getX() - domeData.getDistOeste() ||
                pos.getY() > bloqueDeControl.getY() + domeData.getDistArriba() ||
                pos.getY() < bloqueDeControl.getY() - domeData.getDistAbajo() ||
                pos.getZ() > bloqueDeControl.getZ() + domeData.getDistSur() ||
                pos.getZ() < bloqueDeControl.getZ() - domeData.getDistNorte()
        ){
            return false;
        }

        return true;
    }

    private static void generarBloqueEnPosRandom(ServerLevel level) {
        // ejemplo de como obtener la posicion donde esta el cofre
        DomeWorldData domeData = DomeWorldData.get(level);
        BlockPos bloqueDeControl = domeData.getControllerPos();

        Block[] posiblesBloques = {Blocks.DIAMOND_BLOCK, Blocks.OAK_LOG, Blocks.ICE, Blocks.GLASS, Blocks.GOLD_BLOCK, Blocks.OBSIDIAN, Blocks.TNT};
        BlockPos posRandom = new BlockPos(bloqueDeControl.getX()+((int) (Math.random()*3)+1), bloqueDeControl.getY()+((int) (Math.random()*3)+1), bloqueDeControl.getZ()+((int) (Math.random()*3)+1));
        GameUtil.colocarBloque(level, posRandom, posiblesBloques[(int) (Math.random() * posiblesBloques.length)]);
    }

    private static void obtenerEspadaDiamante(ServerPlayer player){
        // tenemos que indicar el jugador, el objeto y la cantidad
        GameUtil.darItem(player, Items.DIAMOND_SWORD.asItem(), 1);
    }

    private static void perderItemRandom(ServerPlayer player){
        // tenemos que indicar el jugador, el objeto y la cantidad
        List<ItemStack> items = player.getInventory().items;
        for (ItemStack itemStack : items) {
            // Cuidado!! se recorren todos los slots del inventario, ya que para Minecraft un slot vacio es un item de tipo ItemStack.EMPTY
            if (!itemStack.isEmpty()) {
                DomeMod.LOGGER.info("borrando: {}",itemStack.getItem().getName(itemStack).getString());
                GameUtil.quitarItem(player, itemStack.getItem().asItem(), 1);
                break;
            }
        }
    }

    private static void modificarDinero(ServerLevel level, int cantidad) {
        // ejemplo de como obtener la cantidad de dinero y como asignar una nueva
        DomeWorldData domeData = DomeWorldData.get(level);
        int totalMoney = domeData.getDomeTotalMoney();
        totalMoney = totalMoney + cantidad;
        domeData.setDomeTotalMoney(totalMoney);
    }

    private static void comprar(ServerPlayer player, int accionId){

        Item item = compraVenta.get(accionId).getKey();
        int precio = compraVenta.get(accionId).getValue();

        // RESTO DEL DINERO QUE HAY LO QUE CUESTA
        DomeWorldData domeData = DomeWorldData.get(player.getLevel());
        int totalMoney = domeData.getDomeTotalMoney();
        totalMoney = totalMoney - precio;
        domeData.setDomeTotalMoney(totalMoney);

        GameUtil.darItem(player, item, 1);
    }

    private static void vender(ServerPlayer player, int accionId){

        Item item = compraVenta.get(accionId).getKey();
        int precio = compraVenta.get(accionId).getValue();

        // RESTO DEL DINERO QUE HAY LO QUE CUESTA
        DomeWorldData domeData = DomeWorldData.get(player.getLevel());
        int totalMoney = domeData.getDomeTotalMoney();
        totalMoney = totalMoney + precio;
        domeData.setDomeTotalMoney(totalMoney);

        GameUtil.quitarItem(player, item, 1);
    }

}

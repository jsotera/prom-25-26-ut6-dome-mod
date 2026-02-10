package com.jjgames.mods.domemod.block;

import com.jjgames.mods.domemod.blockentity.DomeConfigChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkHooks;
import net.minecraft.server.level.ServerPlayer;

public class DomeConfigChestBlock extends BaseEntityBlock {

    public DomeConfigChestBlock() {
        super(Properties.of(Material.METAL)
                .strength(-1.0F, 3600000.0F)
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DomeConfigChestBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        if (!world.isClientSide) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof DomeConfigChestBlockEntity domeBE) {
                NetworkHooks.openScreen((ServerPlayer) player, domeBE, buf -> buf.writeBlockPos(pos));
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
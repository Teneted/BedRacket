package org.teneted.bedracket.mixin.world.level.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teneted.bedracket.event.block.MoistureChangeEvent;

@Mixin(FarmBlock.class)
public class FarmBlockMixin {

    @WrapOperation(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean bedracket$callMoistureChangeEvent(ServerLevel instance, BlockPos blockPos, BlockState state, int i, Operation<Boolean> original) {
        MoistureChangeEvent event = new MoistureChangeEvent(instance.getBlockState(blockPos).getBlock(), state);
        return !event.isCancelled();
    }
}

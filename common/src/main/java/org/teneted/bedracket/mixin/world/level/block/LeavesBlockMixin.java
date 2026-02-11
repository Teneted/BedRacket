package org.teneted.bedracket.mixin.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.block.LeavesDecayEvent;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {

    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/LeavesBlock;dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"), cancellable = true)
    private void bedracket$callLeavesDecayEvent(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        LeavesDecayEvent event = new LeavesDecayEvent(serverLevel.getBlockState(blockPos).getBlock());
        BedRacket.EVENT_BUS.callEvent(event);

        if (event.isCancelled() || !serverLevel.getBlockState(blockPos).is(((LeavesBlock) (Object) this))) {
            ci.cancel();
            return;
        }
    }
}

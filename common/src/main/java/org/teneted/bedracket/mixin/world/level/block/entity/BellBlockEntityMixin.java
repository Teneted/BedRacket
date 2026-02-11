package org.teneted.bedracket.mixin.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacketEventFactory;

import java.util.List;

@Mixin(BellBlockEntity.class)
public class BellBlockEntityMixin {

    @Inject(method = "makeRaidersGlow", at = @At("HEAD"))
    private static void bedracket$handleBellResonateEvent(Level level, BlockPos blockPos, List<LivingEntity> list, CallbackInfo ci) {
        BedRacketEventFactory.handleBellResonateEvent(level, blockPos, list);
    }
}

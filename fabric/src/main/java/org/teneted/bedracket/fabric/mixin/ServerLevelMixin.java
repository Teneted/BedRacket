package org.teneted.bedracket.fabric.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {

    @Inject(method = "addPlayer", at = @At(value = "HEAD"), cancellable = true)
    private void bedracket$callEntityAddToWorldEvent(ServerPlayer serverPlayer, CallbackInfo ci) {
        EntityAddToWorldEvent event = (EntityAddToWorldEvent) BedRacket.EVENT_BUS.post(EntityAddToWorldEvent.class, new EntityAddToWorldEvent(serverPlayer, ((ServerLevel) (Object) this)));
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}

package org.teneted.bedracket.fabric.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;

@Mixin(ClientLevel.class)
public class ClientLevelMixin {

    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    private void bedracket$callEntityAddToWorldEvent(Entity entity, CallbackInfo ci) {
        EntityAddToWorldEvent event = (EntityAddToWorldEvent) BedRacket.EVENT_BUS.post(EntityAddToWorldEvent.class, new EntityAddToWorldEvent(entity, ((ClientLevel) (Object) this)));
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
}

package org.teneted.bedracket.fabric.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityRemoveFromWorldEvent;

@Mixin(targets = "net.minecraft.client.multiplayer.ClientLevel.EntityCallbacks")
public class ClientLevel_EntityCallbacksMixin {

    @Shadow
    @Final
    private ClientLevel field_27735;

    @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    private void bedracket$callEntityRemoveFromWorldEvent(Entity entity, CallbackInfo ci) {
        BedRacket.EVENT_BUS.post(EntityRemoveFromWorldEvent.class, new EntityRemoveFromWorldEvent(entity, field_27735));
    }
}

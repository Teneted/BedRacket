package org.teneted.bedracket.fabric.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityRemoveFromWorldEvent;

@Pseudo
@Mixin(targets = "net.minecraft.server.level.ServerLevel.EntityCallbacks")
public class ServerLevel_EntityCallbacksMixin {

    @Shadow
    @Final
    private ServerLevel field_26936;

    @Inject(method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V", at = @At("TAIL"))
    private void bedracket$callEntityRemoveFromWorldEvent(Entity entity, CallbackInfo ci) {
        BedRacket.EVENT_BUS.post(EntityRemoveFromWorldEvent.class, new EntityRemoveFromWorldEvent(entity, field_26936));
    }
}

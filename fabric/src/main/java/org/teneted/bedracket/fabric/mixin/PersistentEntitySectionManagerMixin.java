package org.teneted.bedracket.fabric.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;

@Mixin(PersistentEntitySectionManager.class)
public class PersistentEntitySectionManagerMixin<T extends EntityAccess> {

    @Inject(method = "addEntity", at = @At("HEAD"), cancellable = true)
    private void bedracket$callEntityAddToWorldEvent(T entityAccess, boolean bl, CallbackInfoReturnable<Boolean> cir) {
        if (entityAccess instanceof Entity entity) {
            EntityAddToWorldEvent event = (EntityAddToWorldEvent) BedRacket.EVENT_BUS.post(EntityAddToWorldEvent.class, new EntityAddToWorldEvent(entity, entity.level()));
            if (event.isCancelled()) {
                cir.setReturnValue(false);
            }
        }
    }
}

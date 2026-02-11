package org.teneted.bedracket.mixin.world.entity.monster;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.monster.Creeper;
import org.spongepowered.asm.mixin.Mixin;
import org.teneted.bedracket.event.entity.CreeperIgniteEvent;

@Mixin(Creeper.class)
public class CreeperMixin {

    @WrapMethod(method = "ignite")
    private void bedracket$callCreeperIgniteEvent(Operation<Void> original) {
        CreeperIgniteEvent event = new CreeperIgniteEvent(((Creeper) (Object) this), true);
        if (event.callEvent()) {
            original.call();
        }
    }
}

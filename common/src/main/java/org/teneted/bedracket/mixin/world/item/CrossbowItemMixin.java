package org.teneted.bedracket.mixin.world.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.event.entity.EntityLoadCrossbowEvent;

@Mixin(CrossbowItem.class)
public abstract class CrossbowItemMixin {

    @Shadow
    private static boolean tryLoadProjectiles(LivingEntity livingEntity, ItemStack itemStack) {
        return false;
    }

    @Inject(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/CrossbowItem$ChargingSounds;end()Ljava/util/Optional;"), cancellable = true)
    private void bedracket$callEntityLoadCrossbowEvent(ItemStack itemStack, Level level, LivingEntity livingEntity, int i, CallbackInfo ci) {
        final EntityLoadCrossbowEvent event = new EntityLoadCrossbowEvent(livingEntity, itemStack, livingEntity.getUsedItemHand());
        if (!event.callEvent() || !tryLoadProjectiles(livingEntity, itemStack) || !event.shouldConsumeItem()) {
            ci.cancel();
            return;
        }
    }
}

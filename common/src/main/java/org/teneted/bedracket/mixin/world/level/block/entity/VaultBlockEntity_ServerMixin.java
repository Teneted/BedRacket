package org.teneted.bedracket.mixin.world.level.block.entity;

import com.llamalad7.mixinextras.sugar.Cancellable;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.vault.VaultBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacketEventFactory;
import org.teneted.bedracket.event.block.VaultDisplayItemEvent;

@Mixin(VaultBlockEntity.Server.class)
public class VaultBlockEntity_ServerMixin {

    @ModifyArg(method = "cycleDisplayItemFromLootTable", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/vault/VaultSharedData;setDisplayItem(Lnet/minecraft/world/item/ItemStack;)V", ordinal = 1), index = 0)
    private static ItemStack bedracket$callVaultDisplayItemEvent(ItemStack itemStack, @Local(argsOnly = true) ServerLevel serverLevel, @Local(argsOnly = true) BlockPos blockPos, @Cancellable CallbackInfo ci) {
         VaultDisplayItemEvent event = BedRacketEventFactory.callVaultDisplayItemEvent(serverLevel, blockPos, itemStack);
        if (event.isCancelled()) {
            ci.cancel();
            return ItemStack.EMPTY;
        }
        return event.getDisplayItem();
    }
}

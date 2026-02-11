package org.teneted.bedracket.mixin.world.level.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.CrafterBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.BedRacketEventFactory;
import org.teneted.bedracket.event.block.CrafterCraftEvent;

@Mixin(CrafterBlock.class)
public class CrafterBlockMixin {

    @Inject(method = "dispenseFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z", ordinal = 0), cancellable = true)
    private void bedracket$callCrafterCraftEvent(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, CallbackInfo ci, @Local(ordinal = 0) ItemStack itemStack, @Local(ordinal = 0) RecipeHolder<CraftingRecipe> recipeHolder) {
        CrafterCraftEvent event = BedRacketEventFactory.callCrafterCraftEvent(blockPos, serverLevel, itemStack, recipeHolder);
        if (event.isCancelled()) {
            ci.cancel();
            return;
        }
        itemStack = event.getResult();
    }
}

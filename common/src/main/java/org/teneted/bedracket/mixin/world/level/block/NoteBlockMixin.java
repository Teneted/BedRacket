package org.teneted.bedracket.mixin.world.level.block;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.teneted.bedracket.BedRacketEventFactory;
import org.teneted.bedracket.event.block.NotePlayEvent;

@Mixin(NoteBlock.class)
public class NoteBlockMixin {

    @Shadow
    @Final
    public static IntegerProperty NOTE;

    @Inject(method = "triggerEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/properties/NoteBlockInstrument;isTunable()Z"), cancellable = true)
    private void bedracket$callNotePlayEvent(BlockState blockState, Level level, BlockPos blockPos, int i, int j, CallbackInfoReturnable<Boolean> cir, @Local NoteBlockInstrument noteBlockInstrument, @Share("eventNode") LocalIntRef eventNode) {
        NotePlayEvent event = BedRacketEventFactory.callNotePlayEvent(level, blockPos, noteBlockInstrument, blockState.getValue(NOTE));
        eventNode.set(event.getNote());
        if (event.isCancelled()) cir.setReturnValue(false);
    }

    @ModifyArg(method = "triggerEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/NoteBlock;getPitchFromNote(I)F"))
    private int bedracket$useEventNode(int i, @Share("eventNode") LocalIntRef eventNode) {
        return eventNode.get();
    }
}

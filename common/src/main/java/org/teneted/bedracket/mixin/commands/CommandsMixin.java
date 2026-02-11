package org.teneted.bedracket.mixin.commands;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.command.UnknownCommandEvent;

@Mixin(Commands.class)
public class CommandsMixin {

    @ModifyArg(method = "finishParsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/CommandSourceStack;sendFailure(Lnet/minecraft/network/chat/Component;)V"), index = 0)
    private static Component bedracket$callUnknownCommandEvent(Component component, @Local(argsOnly = true) String string, @Local(argsOnly = true) CommandSourceStack commandSourceStack) {
        UnknownCommandEvent event = new UnknownCommandEvent(commandSourceStack, string, component);
        BedRacket.EVENT_BUS.callEvent(event);
        if (event.getMessage() != null) {
            return event.getMessage();
        }
        return component;
    }
}

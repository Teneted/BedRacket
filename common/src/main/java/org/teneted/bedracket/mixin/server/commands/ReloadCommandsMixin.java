package org.teneted.bedracket.mixin.server.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.commands.ReloadCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.event.server.ServerLoadEvent;

import java.util.Collection;

@Mixin(ReloadCommand.class)
public class ReloadCommandsMixin {

    @Inject(method = "reloadPacks", at = @At("HEAD"))
    private static void bedracket$fireServerLoadEvent(Collection<String> collection, CommandSourceStack commandSourceStack, CallbackInfo ci) {
        new ServerLoadEvent(commandSourceStack.getServer(), ServerLoadEvent.LoadType.RELOAD).callEvent();
    }
}

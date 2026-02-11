package org.teneted.bedracket.mixin.server.level;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teneted.bedracket.event.server.MapInitializeEvent;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Shadow
    @NotNull
    public abstract MinecraftServer getServer();

    @Inject(method = "setMapData", at = @At("HEAD"))
    private void bedracket$callMapInitializeEvent(MapId mapId, MapItemSavedData mapItemSavedData, CallbackInfo ci) {
        new MapInitializeEvent(this.getServer(), mapId, mapItemSavedData).callEvent();
    }
}

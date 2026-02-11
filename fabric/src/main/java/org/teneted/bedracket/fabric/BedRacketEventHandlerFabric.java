package org.teneted.bedracket.fabric;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;
import org.teneted.bedracket.event.entity.EntityRemoveFromWorldEvent;
import org.teneted.bedracket.event.server.ServerLoadEvent;

public class BedRacketEventHandlerFabric {

    public static void registerAll() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, serverLevel) -> {
            EntityAddToWorldEvent event = new EntityAddToWorldEvent(entity, serverLevel);
            BedRacket.EVENT_BUS.callEvent(event);
            if (event.isCancelled()) {
                entity.discard();
            }
        });
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, serverLevel) -> {
            new EntityRemoveFromWorldEvent(entity, serverLevel).callEvent();
        });
        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            new ServerLoadEvent(minecraftServer, ServerLoadEvent.LoadType.STARTUP).callEvent();
        });
    }
}

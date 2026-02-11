package org.teneted.bedracket.fabric;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;
import org.teneted.bedracket.event.entity.EntityRemoveFromWorldEvent;

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
    }
}

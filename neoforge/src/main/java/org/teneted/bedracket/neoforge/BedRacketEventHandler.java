package org.teneted.bedracket.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;
import org.teneted.bedracket.event.entity.EntityRemoveFromWorldEvent;

@EventBusSubscriber
public class BedRacketEventHandler {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        EntityAddToWorldEvent bedracketEvent = (EntityAddToWorldEvent) BedRacket.EVENT_BUS.post(EntityAddToWorldEvent.class,
                new EntityAddToWorldEvent(event.getEntity(), event.getLevel()));
        if (bedracketEvent.isCancelled()) {
            event.setCanceled(bedracketEvent.isCancelled());
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        BedRacket.EVENT_BUS.post(EntityRemoveFromWorldEvent.class,
                new EntityRemoveFromWorldEvent(event.getEntity(), event.getLevel()));
    }
}

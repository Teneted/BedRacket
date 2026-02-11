package org.teneted.bedracket.neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;
import org.teneted.bedracket.BedRacket;
import org.teneted.bedracket.event.entity.EntityAddToWorldEvent;
import org.teneted.bedracket.event.entity.EntityRemoveFromWorldEvent;

@EventBusSubscriber
public class BedRacketEventHandlerNeoForge {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        EntityAddToWorldEvent bedracketEvent = new EntityAddToWorldEvent(event.getEntity(), event.getLevel());
        BedRacket.EVENT_BUS.callEvent(bedracketEvent);
        if (bedracketEvent.isCancelled()) {
            event.setCanceled(bedracketEvent.isCancelled());
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
      new EntityRemoveFromWorldEvent(event.getEntity(), event.getLevel()).callEvent();
    }
}

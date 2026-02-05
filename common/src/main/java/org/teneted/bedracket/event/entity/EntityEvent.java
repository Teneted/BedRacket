package org.teneted.bedracket.event.entity;

import net.minecraft.world.entity.Entity;
import org.teneted.bedracket.event.Event;

public class EntityEvent extends Event {

    protected final Entity entity;

    public EntityEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}

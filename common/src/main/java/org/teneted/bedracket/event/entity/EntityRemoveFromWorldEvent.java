package org.teneted.bedracket.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class EntityRemoveFromWorldEvent extends EntityEvent {

    private final Level level;

    public EntityRemoveFromWorldEvent(Entity entity, Level level) {
        super(entity);
        this.level = level;
    }

    /**
     * @return The world that the entity is being added to
     */
    public Level getLevel() {
        return level;
    }
}

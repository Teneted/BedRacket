package org.teneted.bedracket.event.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.teneted.bedracket.event.Cancellable;

public class EntityAddToWorldEvent extends EntityEvent implements Cancellable {

    private final Level level;
    private boolean cancelled;

    public EntityAddToWorldEvent(Entity entity, Level level) {
        super(entity);
        this.level = level;
    }

    /**
     * @return The world that the entity is being added to
     */
    public Level getLevel() {
        return level;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
